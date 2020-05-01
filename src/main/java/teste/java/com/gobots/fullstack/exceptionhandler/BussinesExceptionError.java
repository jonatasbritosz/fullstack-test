package teste.java.com.gobots.fullstack.exceptionhandler;

import java.util.Set;

public class BussinesExceptionError {
    private Integer status;
    private String error;
    private String msgDeveloper;
    Set<String> messages;

    public BussinesExceptionError() {
    }

    public BussinesExceptionError(Integer status, String error, String msgDeveloper, Set<String> messages) {
        this.status = status;
        this.error = error;
        this.msgDeveloper = msgDeveloper;
        this.messages = messages;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMsgDeveloper() {
		return msgDeveloper;
	}

	public void setMsgDeveloper(String msgDeveloper) {
		this.msgDeveloper = msgDeveloper;
	}

	public Set<String> getMessages() {
        return messages;
    }

    public void setMessages(Set<String> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "GeneralExceptionError{" +
                "status=" + status +
                ", error='" + error + '\'' +
                ", msgDeveloper='" + msgDeveloper + '\'' +
                ", messages=" + messages +
                '}';
    }
}
