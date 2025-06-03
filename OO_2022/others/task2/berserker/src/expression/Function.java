package expression;

import java.util.ArrayList;

public class Function {
    private String name;
    private ArrayList<Variable> variables;
    private String content;

    public Function(String name, ArrayList<Variable> variables, String content) {
        this.name = name;
        this.variables = variables;
        this.content = content.replaceAll("x", "{x}").replaceAll("y", "{y}").replaceAll("z", "{z}");
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Variable> getVariables() {
        return this.variables;
    }

    public String getContent() {
        return content;
    }

    public String toString() {
        String output = this.name + "(";
        output += this.variables.get(0).getName();
        for (int i = 1; i < this.variables.size(); i++) {
            output += "," + this.variables.get(i).getName();
        }
        output += ")=" + this.content;
        return output;
    }

}
