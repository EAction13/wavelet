import java.io.IOException;
import java.net.URI;
import java.util;


class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    int num = 0;

    public String handleRequest(URI url) {
        List<String> strList = new ArrayList();
        //String temp = "";
        //The default page with no extra path just shows the number
        if (url.getPath().equals("/")) {
            return Arrays.toString(strList);
        } /*
        //The path /increment adds 1 to the number and displays "Number incremented!"
        else if (url.getPath().equals("/increment")) {
            num += 1;
            return String.format("Number incremented!");
        } 
        //Allows the user to add a number to the current number
        //by entering it as a query after the path /add.
        else {*/
            

            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    strList.add(parameters[1]);
                    return "You added:" + parameters[1];
                }
            }
            else if (url.getPath().contains("/search")){
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    List<String> tempList = new ArrayList();
                    for(int i = 0; i < strList.size(); i++){
                        //If the string at index i contains the specified substring
                        if(strList.get(i).contains(parameters[1])){
                            //Add to new temp array
                            tempList.add(strList.get(i));
                        }
                    }
                    return Arrays.toString(tempList);
                }
            }
            //If the URL is not any of the valid operations, return an error message
            return "404 Not Found!";
        //}
    }
}

class NumberServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
