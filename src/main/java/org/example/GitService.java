package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;

import static org.example.JsonUtil.convertToJsonString;

public class GitService  {

    public static List<RepoDetail> getRepoList(String userName) throws IOException, InterruptedException {

        var apiUrl = "https://api.github.com/users/" + userName + "/repos";
        var request = HttpRequest.newBuilder().GET().uri(URI.create(apiUrl)).build();
        var client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String jsonObject = response.body();

        ObjectMapper objectMapper = new ObjectMapper();
         return objectMapper.readValue(jsonObject, new TypeReference<List<RepoDetail>>(){});
    }


    public static void gitClone(String userName, String password, String url, String repoPath, String xmlFileName) {
        File pomXml = new File(repoPath + xmlFileName);
        if(pomXml.exists()) return;

        try {
            CredentialsProvider cp = new UsernamePasswordCredentialsProvider(userName, password);
            Git git = Git.cloneRepository()
                    .setURI(url)
                    .setDirectory(new File(repoPath))
                    .setCredentialsProvider(cp).call();
        } catch (GitAPIException e) {
            throw new RuntimeException(e);
        }
    }
}
