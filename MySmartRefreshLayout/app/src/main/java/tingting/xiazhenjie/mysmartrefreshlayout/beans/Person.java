package tingting.xiazhenjie.mysmartrefreshlayout.beans;

public class Person {

    public String avatar;
    public String username;
    public String mobile;
    public String bigImage;
    public String content;

    public Person(String avatar, String bigImage, String username) {
        this.avatar = avatar;
        this.bigImage = bigImage;
        this.username = username;
    }

    public Person(String avatar, String username, String mobile, String bigImage, String content) {
        this.avatar = avatar;
        this.username = username;
        this.mobile = mobile;
        this.bigImage = bigImage;
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBigImage() {
        return bigImage;
    }

    public void setBigImage(String bigImage) {
        this.bigImage = bigImage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
