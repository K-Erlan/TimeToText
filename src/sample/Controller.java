package sample;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML
    private Spinner<Integer> hourSpinner;
    final int initialValue = 0;
    SpinnerValueFactory<Integer> valueFactory =
            new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 24, initialValue);
    @FXML
    public Spinner<Integer> minSpinner;
    final int initialValue2 = 0;
    SpinnerValueFactory<Integer> valueFactory2 =
            new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, initialValue2);

    @FXML
    public Label label1;

    @FXML
    public  Label label2;
    @FXML
    public TextField textField;

    public void Trans(javafx.event.ActionEvent event) {
        Map<Integer, String> map = new HashMap<Integer, String>();
        String[] numbers = {"one", "two", "three", "four", "five",
                "six", "seven", "eight", "nine","ten", "eleven",
                "twelve","thirteen", "fourteen", "fifteen", "sixteen", "seventeen","eighteen",
                "nineteen", "twenty", "twenty one", "twenty two", "twenty three", "twenty four",
                "twenty five", "twenty six", "twenty seven", "twenty eight", "twenty nine",
                "thirty", "thirty one", "thirty two", "thirty three", "thirty four",
                "thirty five", "thirty six", "thirty seven", "thirty eight", "thirty nine",
                "forty", "forty one", "forty two", "forty three", "forty four",
                "forty five", "forty six", "forty seven", "forty eight", "forty nine",
                "fifty", "fifty one", "fifty two", "fifty three", "fifty four",
                "fifty five", "fifty six", "fifty seven", "fifty eight", "fifty nine"};
        for(int i = 1; i<60; i++){
            map.put(i, numbers[i-1]);
        }
        String result = "";
        int h = hourSpinner.getValue();
        int m = minSpinner.getValue();
        String min = map.get(m);
        String hour = map.get(h);
        if (m == 0){
            result += "It is " + hour + " o'clock";
        }
        else if((h==24 && m == 0)||(h==0 && m==0)){
            result += "This is midnight";
        }
        else if (m<30 && m>1 && m!=15){
            if (h<=12){
                result += min + " minutes past " + hour + " (A.M.)";
            }
            else {
                hour = map.get(h-12);
                result += min + " minutes past " + hour + " (P.M.)";
            }

        }
        else if(m>30 && m!=45){
            min = map.get(60-m);
            if(h<12){
                hour = map.get(h+1);
                result += min + " minutes to "+ hour + " (A.M.)";
            }
            else{
                hour = map.get(h-11);
                result += min + " minutes to "+ hour + " (P.M.)";
            }
        }
        else if(m==15){

            if(h<12){
                result += "quarter past " + hour + " (A.M.)";
            }
            else{
                result += "quarter past " + hour + " (P.M.)";
            }
        }
        else if(m==30){
            if(h<12){
                result += "half past " + hour + " (A.M.)";
            }
            else{
                result += "half past " + hour + " (P.M.)";
            }
        }
        else if(m==45){
            if(h<12){
                hour = map.get(h+1);

                result += "quarter to " + hour + " (A.M.)";
            }
            else if (h==12){
                hour = "one";
                result += "quarter to " + hour + " (A.M.)";
            }
            else{
                hour = map.get(h-12);
                result += "quarter to " + hour + " (P.M.)";
            }
        }

        if (m==0 && h==0){
            label1.setText("This is twelve (P.M.)");
            label2.setText("This is 12 o'clock in the night");
        }
        else if(m==0 && h>0){
            label1.setText(result.substring(0,1).toUpperCase() + result.substring(1));
            label2.setText("It is " + hour);
        }else
        {
            label1.setText(result.substring(0,1).toUpperCase() + result.substring(1));
            min = map.get(m);
            hour = map.get(h);
            label2.setText(hour.substring(0,1).toUpperCase() + hour.substring(1)+ " " + min);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hourSpinner.setValueFactory(valueFactory);
        minSpinner.setValueFactory(valueFactory2);

    }

    public void TextToSpeech(ActionEvent event) {
        VoiceManager freeVM;
        Voice voice;
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        voice = VoiceManager.getInstance().getVoice("kevin16");
        voice.allocate();
        try{
            voice.speak(label1.getText());
        }catch(Exception e){}
    }
    public void TextToSpeech2(ActionEvent event) {
        VoiceManager freeVM;
        Voice voice;
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        voice = VoiceManager.getInstance().getVoice("kevin16");
        voice.allocate();
        try{
            voice.speak(label2.getText());
        }catch(Exception e){}
    }
    public void CustomText(ActionEvent event){
        String text = textField.getText();
        VoiceManager freeVM;
        Voice voice;
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        voice = VoiceManager.getInstance().getVoice("kevin16");
        voice.allocate();
        try{
            voice.speak(text);
        }catch(Exception e){}
    }


}
