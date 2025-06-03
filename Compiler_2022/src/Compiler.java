import mips.Mid2Mips;
import nonterminals.SyntacticParser;
import optimize.Optimize;
import tools.MyIO;

public class Compiler {
    public static void main(String[] args) throws Exception {
        String text = MyIO.readIn();
        SyntacticParser parser = new SyntacticParser();
        parser.parse(text);
        // System.out.println(SymbolTable.getSymbolTable().toString());
        // MyIO.writeToFile("midCode-before-optimize", MyIO.printMidCode(), false);
        Optimize optimize = new Optimize();
        // MyIO.writeToFile("baseBlocks-before-optimize", optimize.toString(), false);
        optimize.optimize();
        // MyIO.writeToFile("baseBlocks-after-optimize", optimize.toString(), false);
        // MyIO.writeToFile("midCode-after-optimize", MyIO.printMidCode(), false);
        new Mid2Mips().convert();
        // MyIO.writeToFile("error", MyIO.readErrorBuffer());
        // MyIO.check();
        // RegisterOperand.showState();
        // MyIO.writeToFile("mips", "sw $zero, 0x10008000($zero)");
    }
}
