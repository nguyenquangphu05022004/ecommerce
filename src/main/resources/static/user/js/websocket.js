let stomClient = null;
let username = document.getElementById("authen_username").textContent;
const inboxChatUser = document.getElementById("inbox-users")
const inboxChatUser_tablet = document.getElementById("inbox-users-tablet")
const vendorNameInput_tablet = document.getElementById('vendor_name_tablet')
const search_vendor_form_tablet = document.getElementById('search-vendor-tablet')
const messageHistory = document.getElementById('history-message')
const infoChatUser = document.getElementById("info-chat-user")
const form_sendMessage = document.querySelector('#form-message')
let sendConversationId = null;
const message_input = document.querySelector('#write-msg')
const vendorNameInput = document.getElementById('vendor_name')
const baseUrl = window.location.origin
const search_vendor_form = document.getElementById('search-vendor')
const fileIn = document.getElementById('file')
let listUsername = [username];

function onConnect() {
    stomClient.subscribe(
        `/topic/public/listUserMessage/${username}`, receivedListChat)
    stomClient.send(
        `/app/chat.getListUserConversation/${username}`,
        {},
        ''
    )
    stomClient.send(
        `/app/chat.addUser/${username}`,
        {},
        ''
    )
}

function onError() {

}

function connectWebSocket() {
    let socket = new SockJS('/ws');
    stomClient = Stomp.over(socket);
    stomClient.connect({}, onConnect, onError)
}

/**
 *lay danh sach tin nhan cua conversation co id
 */

async function getListMessageOfConverstation(id) {
    if (id.includes('null_')) {
        sendConversationId = null;
        messageHistory.innerHTML = ''
        infoChatUser.innerHTML = `<div class="d-flex align-items-center py-1">
                            <div class="position-relative">
                                <img src="https://bootdey.com/img/Content/avatar/avatar3.png"
                                     class="rounded-circle mr-1" alt="Sharon Lessman" width="40" height="40">
                            </div>
                            <div class="flex-grow-1 pl-3">
                                <strong>${document.getElementById(`conversation-name-${id.split('_')[1]}`).textContent}</strong>
                            </div>
                        </div>`
        listUsername.push(id.split('_')[2])
        document.getElementById("shop-responsible").textContent = "Shop: " + document.getElementById(`conversation-name-${id.split('_')[1]}`).textContent

    } else {
        sendConversationId = id;
        const response = await fetch(`${baseUrl}/message/user/${username}/conversation/${sendConversationId}`);
        const messages = await response.json();
        console.log(messages);
        let html = ''
        for (let i = 0; i < messages.length; i++) {
            let image = ''
            messages[i].urlMedia.forEach(url => {
                image += `<img src="${url}" width="250px"style="cursor: pointer"/>`
            })

            if (messages[i].messageType === 'SEND') {
                html += `<div class="chat-message-right pb-4" xmlns="http://www.w3.org/1999/html">
                                <div>
                                    <img src="${messages[i].userSender.image}"
                                         class="rounded-circle mr-1" alt="Chris Wood" width="40" height="40">
                                    <div class="text-muted small text-nowrap mt-2">${messages[i].updatedAt}</div>
                                </div>
                                <div class="flex-shrink-1 bg-light rounded py-2 px-3 mr-3">
                                    <div class="font-weight-bold mb-1" style="font-weight: bold">Bạn</div>
                                    ${messages[i].content}
                                    <br/>
                                    ${image}
                                </div>
                            </div>`
            } else {
                html += `<div class="chat-message-left pb-4">
                                <div>
                                    <img src="${messages[i].userSender.image}"
                                         class="rounded-circle mr-1" alt="Sharon Lessman" width="40" height="40">
                                    <div class="text-muted small text-nowrap mt-2">${messages[i].updatedAt}</div>
                                </div>
                                <div class="flex-shrink-1 bg-light rounded py-2 px-3 ml-3">
                                    <div class="font-weight-bold mb-1" style="font-weight: bold">${messages[i].userSender.fullName}</div>
                                        ${messages[i].content}
                                        <br/>
                                         ${image}
                                </div>
                            </div>`
            }
        }
        messageHistory.innerHTML = ''
        messageHistory.insertAdjacentHTML('beforeend', html)
        messageHistory.scrollTop = messageHistory.scrollHeight;
        infoChatUser.innerHTML = `<div class="d-flex align-items-center py-1">
                            <div class="position-relative">
                                <img src="${messages[0].conversationResponse.image}"
                                     class="rounded-circle mr-1" alt="Sharon Lessman" width="40" height="40">
                            </div>
                            <div class="flex-grow-1 pl-3">
                                <strong>${messages.length > 0 && messages[0].conversationResponse.conversationName}</strong>
                            </div>
                        </div>`
        document.getElementById("shop-responsible").textContent = "Shop: " + messages[0].conversationResponse.conversationName
    }

}

//send message
async function onSendMessage(event) {
    event.preventDefault();
    if (sendConversationId === null) {
        await fetch(`${baseUrl}/conversations?username=${listUsername[0]}&username=${listUsername[1]}`)
            .then(res => res.json())
            .then(conversation => {
                sendConversationId = conversation.id;
                listUsername.forEach(user => {
                    stomClient.send(
                        `/app/chat.getListUserConversation/${user}`,
                        {},
                        ''
                    )
                })
            })
            .catch(() => alert("Xay ra loi khi tao phong chat"));
    }

    const formData = new FormData();
    formData.append("file", fileIn.files[0]);
    callApiMessage('post',
        formData,
        `/messages?message=${message_input.value}&conversationId=${sendConversationId}`,
        (data) => {
            message_input.value = '';
            fileIn.files[0] = undefined
            fileIn.value = ''
        })


    // stomClient.send(
    //     `/app/chat.sendMessage/${username}/${sendConversationId}`,
    //     {},
    //     JSON.stringify(data)
    // )

}

/**
 *lay danh sach chat cua user
 */
function receivedListChat(payload) {
    const userResponse = JSON.parse(payload.body);
    const listChat = userResponse.conversationResponses;
    let html = ``;
    for (let i = 0; i < listChat.length; i++) {
        stomClient.subscribe(`/topic/public/conversation/${listChat[i].id}`, receivedMessage)
        html += `<a href="#" class="list-group-item list-group-item-action border-0" id='people_chat_${listChat[i].id}' onclick="getListMessageOfConverstation('${listChat[i].id}')">
                            <div class="d-flex align-items-start">
                                <img src="${listChat[i].image}"
                                     class="rounded-circle mr-1" alt="Jennifer Chang" width="40" height="40">
                                <div class="flex-grow-1 ml-3">
                                    <div id='conversation-name-${listChat[i].id}'>${listChat[i].conversationName}</div>
                                    <div class="small"><span class="fas fa-circle ${listChat[i].active === true ? 'chat-online' : 'chat-offline'}"></span> ${listChat[i].active === true ? 'On' : 'Off'}</div>
                                </div>
                            </div>
                        </a>`
    }
    inboxChatUser.innerHTML = `${html}`;
    inboxChatUser_tablet.innerHTML = `${html}`;
}

function receivedMessage(payload) {
    const message = JSON.parse(payload.body)
    if ((message.conversationResponse.id + '') === (sendConversationId + '')) {
        let html = ''
        let image = ''
        message.urlMedia.forEach(url => {
            image += `<img src="${url}" width="250px"style="cursor: pointer"/>`
        })
        if (message.userSender.username === username) {
            html = `<div class="chat-message-right pb-4">
                                <div>
                                    <img src="${message.userSender.image}"
                                         class="rounded-circle mr-1" alt="Chris Wood" width="40" height="40">
                                    <div class="text-muted small text-nowrap mt-2">${message.updatedAt}</div>
                                </div>
                                <div class="flex-shrink-1 bg-light rounded py-2 px-3 mr-3">
                                    <div class="font-weight-bold mb-1" style="font-weight: bold">Bạn</div>
                                    ${message.content}
                                    <br/>
                                         ${image}
                                </div>
                            </div>`
        } else {
            html = `<div class="chat-message-left pb-4">
                                <div>
                                    <img src="${message.userSender.image}"
                                         class="rounded-circle mr-1" alt="Sharon Lessman" width="40" height="40">
                                    <div class="text-muted small text-nowrap mt-2">${message.updatedAt}</div>
                                </div>
                                <div class="flex-shrink-1 bg-light rounded py-2 px-3 ml-3">
                                    <div class="font-weight-bold mb-1" style="font-weight: bold">${message.userSender.fullName}</div>
                                        ${message.content}
                                        <br/>
                                         ${image}
                                </div>
                            </div>`
        }
        messageHistory.insertAdjacentHTML('beforeend', html)
        messageHistory.scrollTop = messageHistory.scrollHeight;
        // getChatInbox.innerHTML = `<p>${message.usernameSender === username ? 'Bạn: ' + message.content : message.userSenderFullName + ': ' + message.content}</p>`
    } else {
        // getChatInbox.innerHTML = `<p style="font-weight: bold">${message.usernameSender === username ? 'Bạn: ' + message.content : message.userSenderFullName + ': ' + message.content}</p>`
    }
}

function searchVendor(inputVendor, inboxVendor) {
    const vendorName = inputVendor.value;
    const response = fetch(`${baseUrl}/vendors/name?name=${vendorName}`);
    response.then(res => res.json())
        .then(vendorResponses => {
            let html = '';
            vendorResponses.forEach(vendor => {
                console.log("vendor: ", vendor)
                console.log("image: ", vendor.user.image)
                if (vendor.user.conversationResponses === null) {
                    console.log("image: ", vendor.user.image)
                    html += `<a href="#" class="list-group-item list-group-item-action border-0" id='null_${vendor.id}' onclick="getListMessageOfConverstation('null_${vendor.id}_${vendor.user.username}')">
                            <div class="d-flex align-items-start">
                                <img src="${vendor.user.image}"
                                     class="rounded-circle mr-1" alt="Jennifer Chang" width="40" height="40">
                                <div class="flex-grow-1 ml-3">
                                    <div id='conversation-name-${vendor.id}'>${vendor.shopName}</div>
                                    <div class="small"><span class="fas fa-circle ${vendor.user.active === true ? 'chat-online' : 'chat-offline'}"></span> ${vendor.user.active === true ? 'On' : 'Off'}</div>
                                </div>
                            </div>
                        </a>`
                } else {
                    vendor.user.conversationResponses.forEach(conversation => {
                        html += `<a href="#" class="list-group-item list-group-item-action border-0" id='people_chat_${conversation.id}' onclick="getListMessageOfConverstation('${conversation.id}')">
                            <div class="d-flex align-items-start">
                                <img src="${conversation.image}"
                                     class="rounded-circle mr-1" alt="Jennifer Chang" width="40" height="40">
                                <div class="flex-grow-1 ml-3">
                                    <div id='conversation-name-${conversation.id}'>${conversation.conversationName}</div>
                                    <div class="small"><span class="fas fa-circle ${conversation.active === true ? 'chat-online' : 'chat-offline'}"></span> ${conversation.active === true ? 'On' : 'Off'}</div>
                                </div>
                            </div>
                        </a>`
                    })
                }
            })
            inboxVendor.innerHTML = `${html}`;
        })
}

function searchVendorByName(e) {
    e.preventDefault();
    sendConversationId = null;
    searchVendor(vendorNameInput, inboxChatUser);
}

function searchVendorTablet(e) {
    e.preventDefault();
    sendConversationId = null;
    searchVendor(vendorNameInput_tablet, inboxChatUser_tablet);
}

form_sendMessage.addEventListener('submit', onSendMessage, true)
search_vendor_form.addEventListener('submit', searchVendorByName, true)
search_vendor_form_tablet.addEventListener('submit', searchVendorTablet, true)



function showImage(imageId) {
    alert(imageId)
}

function callApiMessage(type, data, theUrl, success) {
    const url = new URL(theUrl, document.location);
    console.log(url.href)
    $.ajax({
        type: type,
        url: url.href,
        data: data,
        contentType: false,
        processData: false,
        success: function (data) {
            success(data);
        },
        error: function () {
            alert("Error")
        }
    })
}