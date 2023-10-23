package org.example;


import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static org.example.JsonUtil.convertToJsonString;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner myObj = new Scanner(System.in);

        String gitUser;
        System.out.println("Enter git username to see all repositories");
        gitUser = myObj.nextLine();

        System.out.println(convertToJsonString(GitService.getRepoList(gitUser)));

        String url;
        System.out.println("Enter clone_url of project of which you want the dependencies");
        url = myObj.nextLine();

        String userName;
        System.out.println("Enter your git username");
        userName = myObj.nextLine();

        String password;
        System.out.println("Enter your git password");
        password = myObj.nextLine();

        String repoPath = "/assignment/repo/";
        String xmlFileName = "pom.xml";

        GitService.gitClone(userName, password, url, repoPath, xmlFileName);

        List<DependencyDetail> dependencyDetailList = XmlParserService.getDependencies(repoPath, xmlFileName);
        System.out.println(dependencyDetailList);
    }
}