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
            switch (character) {
                case "G":
                    switch (commandCodes.get(character)) {
                        case ("0") -> cmdDesc.append("Rapid linear movement to point: ");
                        case ("1") -> cmdDesc.append("Feed Linear movement to point: ");
                        case ("2") -> cmdDesc.append("Circular move clockwise: ");
                        case ("3") -> cmdDesc.append("Circular move anti-clockwise");
                        case ("4") -> cmdDesc.append("Dwell time");
                        case ("10") -> cmdDesc.append("Zero offset shift");
                        case ("11") -> cmdDesc.append("Zero offset shift cancel");
                        case ("17") -> cmdDesc.append("Contour plane is XY");
                        case ("18") -> cmdDesc.append("Contour plane is ZX");
                        case ("19") -> cmdDesc.append("Contour plane is YZ");
                        case ("20") -> cmdDesc.append("Inch mode");
                        case ("21") -> cmdDesc.append("MM mode");
                        case ("28") -> cmdDesc.append("Return to reference point");
                        case ("29") -> cmdDesc.append("Return from reference point");
                        case ("30") -> cmdDesc.append("Return to 2nd, 3rd, etc.. reference point");
                        case ("40") -> cmdDesc.append("Cutter compensation off");
                        case ("90") -> cmdDesc.append("Absolute coordinate positioning");
                    }
                    break;
                case "M":
                    switch (commandCodes.get(character)) {
                        case ("0") -> cmdDesc.append("Program stop");
                        case ("1") -> cmdDesc.append("Optional program stop");
                        case ("2") -> cmdDesc.append("End of program, no rewind or return");
                        case ("3") -> cmdDesc.append("Start the spindle in the clockwise direction");
                        case ("4") -> cmdDesc.append("Start the spindle in the anti-clockwise direction");
                        case ("5") -> cmdDesc.append("Stop the spindle");
                        case ("6") -> cmdDesc.append("Tool change command");
                        case ("7") -> cmdDesc.append("Coolant on mist");
                        case ("8") -> cmdDesc.append("Coolant on flood");
                        case ("9") -> cmdDesc.append("Coolant off");
                        case ("13") -> cmdDesc.append("Spindle on clockwise, coolant on");
                        case ("14") -> cmdDesc.append("Spindle on anti-clockwise, coolant on");
                        case ("15") -> cmdDesc.append("spindle off, coolant off");
                        case ("19") -> cmdDesc.append("Spindle orientation on");
                        case ("20") -> cmdDesc.append("Spindle orientation off");
                        case ("30") -> cmdDesc.append("End of program");
                        case ("98") -> cmdDesc.append("Call sub program");
                        case ("99") -> cmdDesc.append("Sub program end");
                    }
                    break;
                case "I":
                    cmdDesc.append("X arc center=" + commandCodes.get(character));
                    break;
                case "J":
                    cmdDesc.append("Y arc center=" + commandCodes.get(character));
                    break;
                case "K":
                    cmdDesc.append("Z arc center=" + commandCodes.get(character));
                    break;
                case "A":
                    cmdDesc.append("Rotary around X=" + commandCodes.get(character));
                    break;
                case "B":
                    cmdDesc.append("Rotary around Y=" + commandCodes.get(character));
                    break;
                case "C":
                    cmdDesc.append("Rotary around Z=" + commandCodes.get(character));
                    break;
                case "R":
                    cmdDesc.append("Drill react");
                    break;
                case "Q":
                    cmdDesc.append("Drill peck");
                    break;
                case "H":
                    cmdDesc.append("Tool length offset");
                    break;
                case "D":
                    cmdDesc.append("cutter dia/rad offset");
                    break;
                case "T":
                    cmdDesc.append(" to tool " + commandCodes.get(character));
                    break;
                case "X":
                case "Y":
                case "Z":
                    cmdDesc.append(character + "=" + commandCodes.get(character) + " ");
                    break;
                case "F":
                    cmdDesc.append("| Feed rate=" + commandCodes.get(character) + " ");
                    break;
                case "P":
                    cmdDesc.append(" for " + commandCodes.get(character));
            }
        }
        return cmdDesc.toString();
    }
}