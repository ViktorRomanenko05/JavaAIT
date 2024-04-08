package homeworkLesson24;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class Community {

    private String communityName;
    private HashSet <User> communityMembers = new HashSet<>();

    private ArrayList<Message> communityMessages;

    public Community(String communityName, HashSet<User> communityMembers, ArrayList<Message> communityMessages) {
        this.communityName = communityName;
        this.communityMembers = communityMembers;
        this.communityMessages = communityMessages;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public HashSet<User> getCommunityMembers() {
        return communityMembers;
    }

    public void setCommunityMembers(HashSet<User> communityMembers) {
        this.communityMembers = communityMembers;
    }

    public ArrayList<Message> getCommunityMessages() {
        return communityMessages;
    }

    public void setCommunityMessages(ArrayList<Message> communityMessages) {
        this.communityMessages = communityMessages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Community community = (Community) o;
        return Objects.equals(communityName, community.communityName) && Objects.equals(communityMembers, community.communityMembers) && Objects.equals(communityMessages, community.communityMessages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(communityName, communityMembers, communityMessages);
    }



    @Override
    public String toString() {
        return "Community{" +
                "communityName='" + communityName + '\'' +
                ", communityMembers=" + communityMembers +
                ", communityMessages=" + communityMessages +
                '}';
    }
}
