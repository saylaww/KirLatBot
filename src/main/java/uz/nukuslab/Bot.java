package uz.nukuslab;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    static String[] kirill = {"Ў", "ў", "Ғ", "ғ", "Ё", "Ц", "Ч", "Ш", "Я", "Ғ", "ғ", "Ў", "ў", "Я", "я", "Ю", "ю", "Ш", "ш", "Ч", "ч", "Ц", "ц", "Ё", "ё", "А", "а", "Б", "б", "В", "в", "Г", "г",
            "Д", "д", "Е", "е", "Ж", "ж", "З", "з",
            "И", "и", "Й", "й", "К", "к", "Л", "л", "М", "м",
            "Н", "н", "О", "о", "П", "п", "Р", "р", "С", "с",
            "Т", "т", "У", "у", "Ф", "ф", "Х", "х",
            "Э", "э",
            "Қ", "қ", "Ҳ", "ҳ", ".", ",", " ", "?", "!"};

    static String[] latin = {"O‘", "o‘", "G‘", "g‘", "Yo", "Ts", "Ch", "Sh", "Ya", "G'", "g'", "O'", "o'", "YA", "ya", "Yu", "yu", "SH", "sh", "CH", "ch", "TS", "ts", "YO", "yo", "А", "a", "B", "b", "V", "v", "G", "g",
            "D", "d", "E", "e", "J", "j", "Z", "z",
            "I", "i", "Y", "y", "K", "k", "L", "l", "M", "m",
            "N", "n", "O", "o", "P", "p", "R", "r", "S", "s",
            "T", "t", "U", "u", "F", "f", "H", "h",
            "E", "e",
            "Q", "q", "X", "x", ".", ",", " ", "?", "!"};
    public String getBotUsername() {
        return "bekmurzaevLatKirConvertbot";
    }

    public String getBotToken() {
        return "1498845248:AAEeUb2TnVXGaqevnalpJKkaBBNwyQ3G6YM";
    }

    public void onUpdateReceived(Update update) {
        String text = update.getMessage().getText();
        String chId = String.valueOf(update.getMessage().getChatId());

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chId);
//        sendMessage.setText(toLatin(text));

        if (check(text).equals("kirill")){
            sendMessage.setText(toLatin(text));
        }else {
            sendMessage.setText(toKirill(text));
        }
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public String check(String str){
        int k = 0;
        int l = 0;
        for (int i = 0; i < str.length(); i++) {
            for (String s : kirill) {
                if (s.equals(String.valueOf(str.charAt(i)))){
                    k++;
//                    return false;
                }
            }
            for (String s : latin) {
                if (s.equals(String.valueOf(str.charAt(i)))){
                    l++;
//                    return false;
                }
            }
        }
        if (k > l){
            return "kirill";
        }else {
            return "latin";
        }
    }

    public String toKirill(String text){
        int counter = 0;
        for (int i = 0; i < text.length() - 1; i++) {
            for (String s : latin) {
                if (!(i == text.length() - 2 || i == text.length() - 1)) {
                    if (s.equals(String.valueOf(text.charAt(i)).concat(String.valueOf(text.charAt(i + 1))))) {
                        i++;
                        counter++;
                    }
                }
            }
        }


        int[] temp = new int[text.length()];
        int tempCounter = 0;

        for (int i = 0; i < text.length(); i++) {

            int idLat = 0;
            int size = latin.length;
            for (String s : latin) {
                if (i == text.length() - 1) {
                    if (s.equals(String.valueOf(text.charAt(i)))) {
                        temp[tempCounter] = idLat;
                        tempCounter++;
                        break;
                    }
                    idLat++;
                } else {
                    if (s.equals(String.valueOf(text.charAt(i)).concat(String.valueOf(text.charAt(i + 1))))) {
                        temp[tempCounter] = idLat;
                        tempCounter++;
                        i++;
                        break;
                    } else if (s.equals(String.valueOf(text.charAt(i)))) {
                        temp[tempCounter] = idLat;
                        tempCounter++;
                        break;
                    }
                    idLat++;
                }

            }
        }

        StringBuilder retValue = new StringBuilder();
        for (int i = 0; i < temp.length - counter; i++) {
            for (int j = 0; j < kirill.length; j++) {
                if (temp[i] == j) {
                    retValue.append(kirill[j]);
                }
            }
        }
        return retValue.toString();
    }

    public String toLatin(String text){
//        scanner = new Scanner(System.in);
        int counter = 0;
        for (int i = 0; i < text.length() - 1; i++) {
            for (String s : latin) {
                if (!(i == text.length() - 2 || i == text.length() - 1)) {
                    if (s.equals(String.valueOf(text.charAt(i)).concat(String.valueOf(text.charAt(i + 1))))) {
                        i++;
                        counter++;
                    }
                }
            }
        }

        int[] temp = new int[text.length() + counter];
        int tempCounter = 0;

        for (int i = 0; i < text.length(); i++) {

            int idKir = 0;
            for (String s : kirill) {
                if (i == text.length() - 1) {
                    if (s.equals(String.valueOf(text.charAt(i)))) {
                        temp[tempCounter] = idKir;
                        tempCounter++;
                        break;
                    }
                    idKir++;
                } else {
                    if (s.equals(String.valueOf(text.charAt(i)).concat(String.valueOf(text.charAt(i + 1))))) {

                        temp[tempCounter] = idKir;
                        tempCounter++;
                        i++;
                        break;
                    } else if (s.equals(String.valueOf(text.charAt(i)))) {
                        temp[tempCounter] = idKir;
                        tempCounter++;

                        break;
                    }
                    idKir++;
                }
            }
        }
        StringBuilder retValue = new StringBuilder();
        for (int i = 0; i < temp.length + counter; i++) {
            for (int j = 0; j < kirill.length; j++) {
                if (temp[i] == j) {
                    retValue.append(latin[j]);
                }
            }
        }
        return retValue.toString();
    }
}
