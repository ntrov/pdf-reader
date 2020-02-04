package Model;

public class Mode {
    private String backGroundColor;
    private String textColor;

    public Mode(){
        backGroundColor = "-fx-background-color: white;";
        textColor = "black";
    }

    public void setMode(String mode){
        if(mode.equals("NightMode")){
            backGroundColor = "-fx-background-color: black;";
            textColor = "white";
        }
        else{
            backGroundColor = "-fx-background-color: white;";
            textColor = "black";
        }
    }


    public String getBackGroundColor(){
        return backGroundColor;
    }

    public String getTextColor(){
        return textColor;
    }
}
