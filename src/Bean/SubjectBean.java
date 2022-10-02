package Bean;

import java.util.List;

public class SubjectBean {


    int ID = 0;

    String Name = null;



    List<String> message = null;

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }



	public List<String>getMessage() {
		return message;
	}

	public void setMessage(List<String> message) {
		this.message = message;
	}



}
