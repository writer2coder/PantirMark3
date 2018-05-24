package pantriapp.russellmcdonald.com.pantirmark3;

public class ProtoRecipe {
    private String protoTitle;
    private int protoImagID;

    public ProtoRecipe(String protoTitle, int protoImagID){
        this.protoTitle = protoTitle;
        this.protoImagID = protoImagID;
    }

    public String getProtoTitle() {
        return protoTitle;
    }

    public void setProtoTitle(String protoTitle) {
        this.protoTitle = protoTitle;
    }

    public int getProtoImagID() {
        return protoImagID;
    }

    public void setProtoImagID(int protoImagID) {
        this.protoImagID = protoImagID;
    }
}
