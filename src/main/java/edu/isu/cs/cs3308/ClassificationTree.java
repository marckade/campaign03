package edu.isu.cs.cs3308;


import edu.isu.cs.cs3308.structures.Node;
import edu.isu.cs.cs3308.structures.impl.BinarySearchTree;
import edu.isu.cs.cs3308.structures.impl.LinkedBinaryTree;
import edu.isu.cs.cs3308.traversals.BreadthFirstTraversal;
import edu.isu.cs.cs3308.traversals.InOrderTraversal;
import edu.isu.cs.cs3308.traversals.PreOrderTraversal;
import edu.isu.cs.cs3308.traversals.TreeTraversal;
import edu.isu.cs.cs3308.traversals.commands.EnumeratedSaveCommand;
import edu.isu.cs.cs3308.traversals.commands.EnumerationCommand;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.List;

/**
 * A very simple classification tree example using a BinaryTree and console
 * input.
 *
 * @author Isaac Griffith
 */
public class ClassificationTree {

    private LinkedBinaryTree<Datum> tree;

    /**
     * Constructs a new Animal tree class which manages an underlying animal
     * tree
     */
    public ClassificationTree() {
        tree = new LinkedBinaryTree<>();
        load();
    }

    public void parse(List<String> treeLines)
    {
        LinkedList<String> tempList = new LinkedList<>();
        LinkedList<Node<Datum>> datumList = new LinkedList<>();

        for (String input : treeLines)
        {
            String[] finalParsed = input.split(":");
            Datum temp = new Datum(finalParsed[3]);

            while (!tempList.isEmpty() && !finalParsed[0].equals(tempList.peek()))
            {
                tempList.poll();
                datumList.poll();
            }

            if (tempList.isEmpty() || !tempList.peek().equals(finalParsed[1]))
            {
                tempList.offer(finalParsed[1]);
            }

            if (tree.isEmpty())
            {
                datumList.offer(tree.setRoot(temp));
            }
            else {

                if (finalParsed[2].equals("l"))
                {
                    datumList.offer(tree.addLeft(datumList.peek(), temp));
                } else
                    {
                    datumList.offer(tree.addRight(datumList.peek(), temp));
                }
            }
        }
    }

    public void hardParse()
    {
        tree.setRoot(new Datum("Nothing"));
    }


    private void askQuestions(Node<Datum> currentNode, String userInput) {
        Scanner scan = new Scanner(System.in);

        if (userInput.equals("Y"))
        {
            if (tree.isExternal(currentNode))
            {
                System.out.println("Good.\n");
            }
            else if (tree.left(currentNode) == null)
            {
                askQuestions(currentNode, "N");
            }
            else {

                askQuestions(tree.left(currentNode), "");
            }
        }
        else if (userInput.equals("N"))
        {
            if (tree.numChildren(currentNode) > 1)
            {
                askQuestions(tree.right(currentNode), "");
            }
            else {
                System.out.println("I don't know any " + notAnimalString(currentNode) +
                        " animals that aren't " + currentNode.getElement());
                System.out.println("What is the new animal? > ");
                String inputAnimal = scan.next();

                System.out.println("What characteristic does a " + inputAnimal + " have that " +
                        currentNode.getElement() + " does not? > ");
                userInput = scan.next();
                System.out.println();

                currentNode = tree.insert(new Datum(userInput), currentNode);
                tree.insert(new Datum("a " + inputAnimal), currentNode);
            }
        }
        else {
            System.out.println("Is this animal " + currentNode.getElement() + "? (Y/N) > ");
            userInput = scan.next().toUpperCase();
            askQuestions(currentNode,userInput);
        }
    }

    /**
     * Get the string of the parent tree, for when an animal is not known
     * @param currNode The current node that the identify does not know
     * @return String of all of the parent animal descriptors
     */
    private String notAnimalString(Node<Datum> currNode) {
        String retString = "";
        int depth = tree.depth(currNode);

        for (int i = 0; i < depth; i++) {
            currNode = currNode.getParent();

            if (i != 0) {
                retString = ", " + retString;
            }
            retString = currNode.getElement() + retString;
        }
        return retString;
    }

    /**
     * Main method which controls the identification and tree management loop.
     */
    public void identify() {
        askQuestions(tree.root(), "");
    }

    /**
     * Saves a tree to a file.
     */
    public void save() {
        TreeTraversal<Datum> tempTraversal = new InOrderTraversal<>(tree);

        tempTraversal.setCommand(new EnumerationCommand());

        tempTraversal.traverse();

        tempTraversal = new BreadthFirstTraversal<>(tree);

        Scanner scan = new Scanner(System.in);
        System.out.println("Filename of tree for saving: ");
        String filename = scan.next();

        try
        {
            Path p = Paths.get(filename);
            Files.deleteIfExists(p);
        }
        catch (Exception ex)
        {
            System.out.println(ex.toString());
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(new File(filename), false)))
        {
            tempTraversal.setCommand(new EnumeratedSaveCommand(writer));
            tempTraversal.traverse();
        }
        catch (IOException ex)
        {
            System.out.println(ex.toString());
        }
    }

    /**
     * Loads a tree from the given file, if an exception occurs during file
     * operations, a hardcoded basic tree will be loaded instead.
     */
    public void load() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Filename of tree to load: ");

        String input = scan.next();
        System.out.println();

        if (Files.exists(Paths.get(input)))
        {
            List<String> treeLines = new LinkedList<>();

            try
            {
                treeLines = Files.readAllLines(Paths.get(input));
            }
            catch (IOException ex)
            {
                System.out.println(ex.toString());
            }

            parse(treeLines);
        }
        else {
            hardParse();
        }
    }
}
