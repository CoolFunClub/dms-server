package com.coolfunclub.dms.hello;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import java.util.List;

@RestController
public class HelloWorldController {
    @RequestMapping("/HelloWorld")
    public String helloWorld(){
        return "Hello World!";
    }

    @RequestMapping("/kml")
    public String cats() {
        return "(^=◕ᴥ◕=^)";
    }


    @RequestMapping("/dog")
    public String dog() {
        return "Here U go!";
    }


    @RequestMapping("/processStrings")
    public List<String> processStrings(@RequestParam("inputStrings") List<String> inputStrings) {
        // Join the input strings into a single string using Guava's Joiner
        String joinedString = Joiner.on(", ").join(inputStrings);

        // Split the joined string back into a list of strings using Guava's Splitter
        Iterable<String> splitStrings = Splitter.on(", ").split(joinedString);

        // Convert the Iterable to a List
        List<String> processedStrings = Lists.newArrayList(splitStrings); // Using Guava's Lists.newArrayList()

        return processedStrings;
    }

}
