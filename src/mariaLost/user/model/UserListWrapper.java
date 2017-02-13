package mariaLost.user.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by elsacollet on 24/01/2017.
 * This class permit us to register player in a xml file as a list of player
 */

@XmlRootElement(name = "UserListWrapper")
public class UserListWrapper {
    private List<User> listWrapperUser;

    @XmlElement(name = "user")
    public List<User> getListWrapperUser() {
        return listWrapperUser;
    }

    public void setListWrapperUser(List<User> list) {
        this.listWrapperUser = list;
    }
}
