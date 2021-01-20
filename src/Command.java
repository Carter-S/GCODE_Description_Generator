import java.util.LinkedHashMap;

public class Command {
    private String line;
    public LinkedHashMap<String, String> commandCodes;

    public Command(String command){
        this.line = command;
        commandCodes = new LinkedHashMap<>();
        System.out.println(command);
        extractCommands();
        System.out.println(commandDescription());
    }

    public void extractCommands(){
        String tmpL="";
        String tmpN="";
        char[] cmd = new char[line.length()+1];
        for(int i = 0; i < line.length(); i++){
            cmd[i] = line.charAt(i);
        }
        cmd[cmd.length-1] = ' ';
        for(int i = 0; i < cmd.length; i++){
            if(Character.isDigit(cmd[i]) || cmd[i] == '.' || cmd[i] == '-'){
                tmpN = tmpN + cmd[i];
            }else if(!Character.isDigit(cmd[i]) && cmd[i] != ' '){
                tmpL = tmpL + cmd[i];
            }else if(cmd[i] == ' '){
                commandCodes.put(tmpL, tmpN);
                tmpL = "";
                tmpN = "";
            }
        }
    }

    public String commandDescription(){
        StringBuilder cmdDesc = new StringBuilder();
        for (String character : commandCodes.keySet()){
            if(character.equals("G")){
                switch (commandCodes.get(character)) {
                    case ("0") -> cmdDesc.append("Rapid linear movement to point: ");
                    case ("1") -> cmdDesc.append("Feed Linear movement to point: ");
                    case ("21") -> cmdDesc.append("MM mode");
                    case ("90") -> cmdDesc.append("Absolute coordinate positioning");
                }
            }
            if(character.equals("M")){
                switch (commandCodes.get(character)) {
                    case ("6") -> cmdDesc.append("Tool change command");
                    case ("30") -> cmdDesc.append("Program finished");
                }
            }
            if(character.equals("T")){
                cmdDesc.append(" to tool " + commandCodes.get(character));
            }
            if(character.equals("X")|| character.equals("Y") || character.equals("Z")){
                cmdDesc.append(character + "=" + commandCodes.get(character) + " ");
            }
            if(character.equals("F")){
                cmdDesc.append("| Feed rate=" + commandCodes.get(character) + " ");
            }
        }
        return cmdDesc.toString();
    }
}