package cardscucumber;

class IsItFriday{
    static String isItFriday(String today){
        return today.equals("Friday") ? "TGIF" : "Nope";
    }
}