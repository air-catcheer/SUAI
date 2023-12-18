import java.util.ArrayList;


public class AdList {
    private ArrayList<Ad> base = new ArrayList<>();
    public AdList(){}

    public static Ad getAdById(String adId) {

        return null;

    }

    public synchronized void add(String h, String d, String n, String t) {
        base.add(new Ad(h,d,n,t));
    }
    public synchronized Ad getAd(int index) {
        return base.get(index);
    }
    public int size(){
        return base.size();
    }
    public class Ad {
        public String header;
        public String name;
        public String description;
        public String time;

        public Ad(String h, String d, String n, String t) {
            header = h;
            description = d;
            name = n;
            time = t;

        }


        public String getHeader() {
            return header;
        }

        public String getDescription() {
            return description;
        }

        public String getName() {
            return name;
        }

        public String getTime() {
            return time;
        }

        public void addReply(String text) {
        }
    }


    public static class Reply {
        //список ответов на объявление:
        public String text;
        public String name;
        public String time;

        public Reply(String t, String n, String ti) {
            text = t;
            name = n;
            time = ti;
        }

        public Reply() {

        }
    }
}
