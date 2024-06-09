
let stomClient = null;
let username = document.getElementById("authen_username").textContent;
const inboxChatUser = document.getElementById("inbox-users")
const messageHistory = document.getElementById('history-message')
const infoChatUser = document.getElementById("info-chat-user")
const form_sendMessage = document.querySelector('#form-message')
let sendConversationId = null;
const message_input = document.querySelector('#write-msg')
const search_username_button = document.getElementById('search-username')
const input_username = document.getElementById("search-input-username")
const baseUrl = window.location.origin
function onConnect() {
    console.log("username: ", username)
    stomClient.subscribe(
        `/topic/public/listUserMessage/${username}`, receivedListChat)
    stomClient.send(
        `/app/chat.getListUserConversation/${username}`,
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

async function getListMessageOfConverstation(conversationId) {
    sendConversationId = conversationId;
    if (sendConversationId.includes('null_')) {
        fetch(`${baseUrl}/conversations?username=${username}&username=${sendConversationId.split('_')[1]}`)
            .then(res => res.json())
            .then((conversation) => {
                stomClient.send(
                    `/app/chat.getListUserConversation/${username}`,
                    {},
                    ''
                )
                stomClient.send(
                    `/app/chat.getListUserConversation/${sendConversationId.split('_')[1]}`,
                    {},
                    ''
                )
                sendConversationId = conversation.id;
            })
            .catch(err => {
                console.log(err)
            })

    }
    const response = await fetch(`${baseUrl}/message/user/${username}/conversation/${sendConversationId}`);
    const messages = await response.json();
    console.log(messages);
    let html = ''
    for (let i = 0; i < messages.length; i++) {
        if (messages[i].messageType === 'SEND') {
            html += `<div class="chat-message-right pb-4">
                                <div>
                                    <img src="https://bootdey.com/img/Content/avatar/avatar1.png"
                                         class="rounded-circle mr-1" alt="Chris Wood" width="40" height="40">
                                    <div class="text-muted small text-nowrap mt-2">${messages[i].updatedAt}</div>
                                </div>
                                <div class="flex-shrink-1 bg-light rounded py-2 px-3 mr-3">
                                    <div class="font-weight-bold mb-1">Bạn</div>
                                    ${messages[i].content}
                                </div>
                            </div>`
        } else {
            html += `<div class="chat-message-left pb-4">
                                <div>
                                    <img src="https://bootdey.com/img/Content/avatar/avatar3.png"
                                         class="rounded-circle mr-1" alt="Sharon Lessman" width="40" height="40">
                                    <div class="text-muted small text-nowrap mt-2">${messages[i].updatedAt}</div>
                                </div>
                                <div class="flex-shrink-1 bg-light rounded py-2 px-3 ml-3">
                                    <div class="font-weight-bold mb-1">${messages[i].userSender.fullName}</div>
                                        ${messages[i].content}
                                </div>
                            </div>`
        }
    }
    console.log(html)
    messageHistory.innerHTML =''
    messageHistory.insertAdjacentHTML('beforeend', html)
    messageHistory.scrollTop = messageHistory.scrollHeight;
    infoChatUser.innerHTML = `<div class="d-flex align-items-center py-1">
                            <div class="position-relative">
                                <img src="https://bootdey.com/img/Content/avatar/avatar3.png"
                                     class="rounded-circle mr-1" alt="Sharon Lessman" width="40" height="40">
                            </div>
                            <div class="flex-grow-1 pl-3">
                                <strong>${messages.length > 0 && messages[0].conversationResponseMessage.conversationName}</strong>
                            </div>
                        </div>`


}

function onSendMessage(event) {
    event.preventDefault();
    stomClient.send(
        `/app/chat.sendMessage/${username}/${sendConversationId}`,
        {},
        JSON.stringify({
            'content': message_input.value
        })
    )
    message_input.value = ''
}

function receivedListChat(payload) {
    const userResponse = JSON.parse(payload.body);
    const listChat = userResponse.conversationResponses;
    let html = ``;
    for (let i = 0; i < listChat.length; i++) {
        stomClient.subscribe(`/topic/public/conversation/${listChat[i].id}`, receivedMessage)
        html += `<a href="#" class="list-group-item list-group-item-action border-0" id='people_chat_${listChat[i].id}' onclick="getListMessageOfConverstation('${listChat[i].id}')">
                            <div class="d-flex align-items-start">
                                <img src="https://bootdey.com/img/Content/avatar/avatar3.png"
                                     class="rounded-circle mr-1" alt="Jennifer Chang" width="40" height="40">
                                <div class="flex-grow-1 ml-3">
                                    ${listChat[i].conversationName}
                                    <div class="small"><span class="fas fa-circle chat-offline"></span> Offline</div>
                                </div>
                            </div>
                        </a>`
    }
    console.log(html)
    inboxChatUser.innerHTML = `${html}`;
}

function receivedMessage(payload) {
    const message = JSON.parse(payload.body)
    const getChatInbox = document.getElementById(`people_chat_${message.conversationResponseMessage.id}`)
    if ((message.conversationResponseMessage.id + '') === (sendConversationId + '')) {
        let html = ''
        if (message.userSender.username === username) {
            html = `<div class="chat-message-right pb-4">
                                <div>
                                    <img src="https://bootdey.com/img/Content/avatar/avatar1.png"
                                         class="rounded-circle mr-1" alt="Chris Wood" width="40" height="40">
                                    <div class="text-muted small text-nowrap mt-2">${message.updatedAt}</div>
                                </div>
                                <div class="flex-shrink-1 bg-light rounded py-2 px-3 mr-3">
                                    <div class="font-weight-bold mb-1">Bạn</div>
                                    ${message.content}
                                </div>
                            </div>`
        } else {
            html = `<div class="chat-message-left pb-4">
                                <div>
                                    <img src="https://bootdey.com/img/Content/avatar/avatar3.png"
                                         class="rounded-circle mr-1" alt="Sharon Lessman" width="40" height="40">
                                    <div class="text-muted small text-nowrap mt-2">${message.updatedAt}</div>
                                </div>
                                <div class="flex-shrink-1 bg-light rounded py-2 px-3 ml-3">
                                    <div class="font-weight-bold mb-1">${message.userSender.fullName}</div>
                                        ${message.content}
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

function searchUsername(e) {
    e.preventDefault();
    const usernameSearch = input_username.value;
    const response = fetch(`${baseUrl}/users?username=${usernameSearch}`);
    response.then(res => res.json())
        .then(userResponse => {
            inboxChatUser.innerHTML = `<div class="chat_list" id="${'null_' + userResponse.username}" style="cursor: pointer" onclick="getListMessageOfConverstation('${'null_' + userResponse.username}')">
                        <div class="chat_people">
                            <div class="chat_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
                            <div class="chat_ib">
                                <h5>${userResponse.fullName}<span class="chat_date">Dec 25</span></h5>
                            </div>
                        </div>
                    </div>`
        })


}

form_sendMessage.addEventListener('submit', onSendMessage, true)
search_username_button.addEventListener('submit', searchUsername, true)