package mariaLost.player.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by elsacollet on 24/01/2017.
 * This class permit us to register player in a xml file as a list of player
 */

@XmlRootElement
public class PlayerListWrapper {
    private List<Player> listWrapperPlayer;

    @XmlElement(name="player")
    public List<Player> getListWrapperPlayer(){
        return listWrapperPlayer;
    }

    public void setListWrapperPlayer(List<Player> list){
        this.listWrapperPlayer=list;
    }
}
