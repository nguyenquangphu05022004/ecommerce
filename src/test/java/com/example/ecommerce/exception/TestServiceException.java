package com.example.ecommerce.exception;


import com.example.ecommerce.TestBase;
import com.example.ecommerce.frame.common.exception.ErrorCode;
import com.example.ecommerce.frame.common.exception.ExceptionMessage;
import com.example.ecommerce.frame.common.exception.ServiceException;
import com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class TestServiceException extends TestBase {

    @Test
    public void test() {
        @ExceptionMessage(message = "test exception") String name = "template_name";
        ErrorCode errorCode = new ErrorCode("dsakfksfasfdsfs", 5555);
        ServiceException serviceException = ServiceExceptionUtils.exception(errorCode, name);

        assertEquals(serviceException.getMessage().contains("test exception"), true);
    }
}
