package ru.dreamjteam.other;


public enum StatusCode {
    
    NEW, EXECUTING, DONE, CANCELED;
    
    public StatusCode getStatus(String status) {
        return StatusCode.valueOf(status.toUpperCase());
    }
    
    public String toRusStatus(String status)
	{
		switch (getStatus(status)) {
			case NEW: return "Новый";
			case EXECUTING: return "Выполняется";
			case DONE: return "Выполен";
			case CANCELED: return "Отменен";
			default:
				throw new IndexOutOfBoundsException();
		}
	}	
    
}
