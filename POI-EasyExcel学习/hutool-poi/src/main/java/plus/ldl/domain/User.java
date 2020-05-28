package plus.ldl.domain;

/**
 * @author ldl.plus
 * @date 2020年05月19日  23:40
 */
public class User {
    private int id;
    private int group;
    private int queue;
    private String relationship;
    private String name;
    private String idcard;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", group=" + group +
                ", queue=" + queue +
                ", relationship='" + relationship + '\'' +
                ", name='" + name + '\'' +
                ", idcard='" + idcard + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getQueue() {
        return queue;
    }

    public void setQueue(int queue) {
        this.queue = queue;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }
}
