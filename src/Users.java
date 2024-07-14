public class Users {
    String userName;
    String pass;
    int levelNum;
    int remainingCoins1;
    int remainingCoins2;
    int remainingCoins3;
    int remainingCoins4;
    int remainingCoins5;
    public Users(String userName, String pass) {
        this.userName = userName;
        this.pass = pass;
        this.levelNum=1;
        this.remainingCoins1=0;
        this.remainingCoins2=0;
        this.remainingCoins3=0;
        this.remainingCoins4=0;
        this.remainingCoins5=0;
    }

    public Users(String userName, String pass, int levelNum) {
        this.userName = userName;
        this.pass = pass;
        this.levelNum = levelNum;
    }

    public Users() {
        this.levelNum=1;
        this.remainingCoins1=0;
        this.remainingCoins2=0;
        this.remainingCoins3=0;
        this.remainingCoins4=0;
        this.remainingCoins5=0;
    }
}