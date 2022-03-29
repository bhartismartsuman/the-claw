import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            File inputFile = new File("src/test/resources/input.txt");
            Main.solution(inputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void solution(File input) throws Exception {
        Scanner scan = new Scanner(input);
        String line = "";
        List<Integer> tempList = new ArrayList<>();
        while (scan.hasNextLine()) {
            line = scan.nextLine().trim();
            String[] commands = line.split(" ");
            if (commands[0].equalsIgnoreCase("size")) {
                int size = Main.getValueFromArray(commands, 1);
                for (int i = 0; i < size; i++) {
                    tempList.add(0);
                }
            }
            else if (commands[0].equalsIgnoreCase("add")) {
                int add = Main.getValueFromArray(commands, 1);
                Main.add(tempList, add);
            }
            else if (commands[0].equalsIgnoreCase("mv")) {
                int moveFrom = Main.getValueFromArray(commands, 1);
                int moveTo = Main.getValueFromArray(commands, 2);
                Main.move(tempList, moveFrom, moveTo);
            }
            else if (commands[0].equalsIgnoreCase("rm")) {
                int rm = Main.getValueFromArray(commands, 1);
                Main.remove(tempList, rm);
            }
            else {
                System.out.println("Command is not correct, command : "+ line);
                break;
            }
        }
        FileWriter writer = new FileWriter("src/main/resources/output.txt");
        for (int i = 0; i < tempList.size(); i++) {
            String outputLine = i+1+":";
            if (tempList.get(i) > 0) {
                outputLine+=" ";
            }
            for (int j = 0; j < tempList.get(i); j++) {
                outputLine+='X';
            }
            writer.write(outputLine);
            if (i<tempList.size()-1) {
                writer.write("\n");
            }
        }
        writer.close();
    }

    public static List<Integer> add(List<Integer> list,int slot) throws Exception {
        if(slot>list.size()) {
            throw new Exception("no slots available to add the blocks");
        }
        int numberOfBlockAvailable = list.get(slot-1);
        list.set(slot-1,numberOfBlockAvailable+1);
        return list;
    }

    public static List<Integer> remove(List<Integer> list,int slot) throws Exception {
        if(slot>list.size()) {
            throw new Exception("no slots available to remove the blocks");
        }
        int numberOfBlockAvailable = list.get(slot-1);
        if (numberOfBlockAvailable < 1) {
            throw new Exception("no blocks available to remove from given stack");
        }
        list.set(slot-1,numberOfBlockAvailable-1);
        return list;
    }

    public static List<Integer> move(List<Integer> list,int slot1,int slot2) throws Exception {
        if(slot1>list.size() || slot2>list.size()) {
            throw new Exception("no slots available to move the blocks");
        }
        int numberOfBlockAvailableAtSlot1 = list.get(slot1-1);
        if(numberOfBlockAvailableAtSlot1<1) {
            throw new Exception("no blocks available to move from given stack");
        }
        list.set(slot1-1,numberOfBlockAvailableAtSlot1-1);
        int numberOfBlockAvailableAtSlot2 = list.get(slot2-1);
        list.set(slot2-1,numberOfBlockAvailableAtSlot2+1);
        return list;
    }

    public static int getValueFromArray(String[] arr, int index)
            throws ArrayIndexOutOfBoundsException,NumberFormatException {
        try {
            return Integer.parseInt(arr[index]);
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            throw new ArrayIndexOutOfBoundsException("command is not correct : "+aiobe);
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException("command is not correct : "+nfe);
        }
    }

}
