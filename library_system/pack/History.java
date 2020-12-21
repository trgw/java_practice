package pack;

import java.util.Date;

public class History {
    Date lendDate;
    Date returnDate;
    Book book;
    User user;

    Boolean isLent(){
        if (returnDate == null){
            return true;
        } else {
            return false;
        }
    }
}