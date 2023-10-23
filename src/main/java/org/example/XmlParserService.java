package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlParserService {
    public static final String DEPENDENCY_TAG = "dependency";
    public static final String GROUP_ID_TAG = "groupId";
    public static final String ARTIFACT_ID_TAG = "artifactId";
    public static final String VERSION_TAG = "version";
    public static List<DependencyDetail> getDependencies(String repoPath, String xmlFileName) {
        List<DependencyDetail> dependencyDetailList = new ArrayList<>();

        try {
            File input = new File(repoPath+xmlFileName);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(input);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName(DEPENDENCY_TAG);
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    Node groupIdNode = eElement
                            .getElementsByTagName(GROUP_ID_TAG)
                            .item(0);
                    Node artifactIdNode = eElement
                            .getElementsByTagName(ARTIFACT_ID_TAG)
                            .item(0);
                    Node versionNode = eElement
                            .getElementsByTagName(VERSION_TAG)
                            .item(0);
                    DependencyDetail dependencyDetail = new DependencyDetail(groupIdNode,artifactIdNode,versionNode);
                    dependencyDetailList.add(dependencyDetail);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dependencyDetailList;
    }
}
