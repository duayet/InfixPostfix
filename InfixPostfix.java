
import java.util.Scanner;
import java.util.Arrays;

public class InfixPostfix{
     char[]x;
     char[]y;
     String output = "";
     int top;
     int count = 1; 
    InfixPostfix (){
        System.out.print("\t\t===Infix to Postfix====\n");
       System.out.print("Enter Infix, put # after the expression: ");
        convert(input());
        prepare(x.length);

        System.out.println("Move"+"\t"+"Token\t\tStack\t\tOutput\n");
        
        for (int i = 0; i < x.length; i++) {
            if (Character.isLetterOrDigit(x[i])) {
                output += x[i];
                display(x[i]);
            } else if (x[i] == '(') {
                push(x[i]);
                display(x[i]);
            } else if (x[i] == ')') {
                while (y[top] != '(') {
                    output += y[top];
                    pop();
                }
                pop();
                display(x[i]);
            } else if (x[i] == ' ') {
                while (top != -1) {
                    output += y[top];
                    pop();
                }
                display(x[i]);
            } else {   
                if (top == -1) {
                    push(x[i]);
                    display(x[i]);
                } else if (x[i] == '+' || x[i] == '-') {
                    if (y[top] == '+' || y[top] == '-' || y[top] == '*' || y[top] == '/' || y[top] == '%' || y[top] == '^') {
                        output += y[top];
                        pop();
                        push(x[i]);
                        display(x[i]);
                    } else {
                        push(x[i]);
                        display(x[i]);
                    }
                } else if (x[i] == '*' || x[i] == '/' || x[i] == '%') {
                    if (y[top] == '*' || y[top] == '/' || y[top] == '%' || y[top] == '^') {
                        output += y[top];
                        pop();
                        push(x[i]);
                        display(x[i]);
                    } else {
                        push(x[i]);
                        display(x[i]);
                    }
                } else if (x[i] == '^') {
                    push(x[i]);
                    display(x[i]);
                }
            }
        }

        System.out.println("\nOutput: " + output);
    }

    void prepare(int size){
        y = new char[size];
        top = -1;


    }
     String input(){
        Scanner sc = new Scanner(System.in);
        String n = sc.nextLine();
        return n;
    }
    
    void push(char input){
        top++;
        y[top]=input;
    }
    
   void pop() {
        y[top] = '\0';
        top--;
    }

     void convert(String input) {
        x = input.toCharArray();
        x = addEle(x, ' ');
    }

     char[] addEle(char[] arr, char ele) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = ele;
        return arr;
    }

    void display(char input) {
        String stack = " ";

        for (int i = 0; i < top + 1; i++) {
            stack += y[i];
        }
        System.out.println((count)+"\t"+input + "\t\t" + stack + "\t\t" + output);
        count++;
    }

    public static void main(String[] args) {
        new InfixPostfix();
    }

}