package it.valmai;

import java.util.*;

public class PipeMaze {

    public static void main(String[] args) {

        List<List<String>> pipes = new ArrayList<>();
        AocInputReader.getLinesFromInput("input.txt").stream()
                .map(line -> Arrays.asList(line.split("")))
                .forEach(pipes::add);

        Integer[] start = findStart(pipes);

        Queue<Integer[]> q = new ArrayDeque<>();
        List<Integer[]> loop = new ArrayList<>();
        loop.add(new Integer[]{start[0], start[1]});
        q.add(new Integer[]{start[0], start[1]});

        while (!q.isEmpty()) {
            Integer[] pair = q.poll();
            int r = pair[0];
            int c = pair[1];
            String current = pipes.get(r).get(c);
            // Go up if the current pipe is a vertical one and the pipe above is a vertical one or a bend
            if (r > 0 && "S|JL" .contains(current) && "|7F" .contains(pipes.get(r - 1).get(c))
                    && !isInLoop(loop, r - 1, c)) {
                loop.add(new Integer[]{r - 1, c});
                q.add(new Integer[]{r - 1, c});
            }
            // Go down if the current pipe is a vertical one and the pipe below is a vertical one or a bend
            if (r < pipes.size() - 1 && "S|7F" .contains(current) && "|JL" .contains(pipes.get(r + 1).get(c))
                    && !isInLoop(loop, r + 1, c)) {
                loop.add(new Integer[]{r + 1, c});
                q.add(new Integer[]{r + 1, c});
            }
            // Go left if the current pipe is a horizontal one and the pipe to the left is a horizontal one or a bend
            if (c > 0 && "S-J7" .contains(current) && "-LF" .contains(pipes.get(r).get(c - 1))
                    && !isInLoop(loop, r, c - 1)) {
                loop.add(new Integer[]{r, c - 1});
                q.add(new Integer[]{r, c - 1});
            }
            //  Go right if the current pipe is a horizontal one and the pipe to the right is a horizontal one or a bend
            if (c < pipes.get(r).size() - 1 && "S-LF" .contains(current) && "-J7" .contains(pipes.get(r).get(c + 1))
                    && !isInLoop(loop, r, c + 1)) {
                loop.add(new Integer[]{r, c + 1});
                q.add(new Integer[]{r, c + 1});
            }
        }

        // Part 2
        List<Integer[]> freeTiles = new ArrayList<>();
        for (int i = 0; i < pipes.get(0).size(); i++) {
            for (int j = 0; j < pipes.size(); j++) {
                if (!isInLoop(loop,j,i)) {
                    freeTiles.add(new Integer[]{i, j});
                }
            }
        }
        int nestPoints = 0;
        for (Integer[] groundPoint : freeTiles) {
            if (isInsidePoint(groundPoint, loop, pipes)) {
                nestPoints++;
            }
        }

        System.out.println("Max loop distance: " + loop.size()/2);
        System.out.println("Internal nest points: " + nestPoints);
    }

    private static Integer[] findStart(List<List<String>> pipes) {
        Integer[] start = new Integer[2];
        for (int i = 0; i < pipes.get(0).size(); i++) {
            for (int j = 0; j < pipes.size(); j++) {
                if (pipes.get(j).get(i).equals("S")) {
                    start[0] = j;
                    start[1] = i;
                }
            }
        }
        return start;
    }

    private static boolean isInLoop(List<Integer[]> loop, int r, int c) {
        for (Integer[] pair : loop) {
            if (pair[0] == r && pair[1] == c) {
                return true;
            }
        }
        return false;
    }

    private static boolean isInsidePoint(Integer[] point, List<Integer[]> loop, List<List<String>> pipes){
        List<String> crossPipe = List.of("|", "J", "L");
        int x = point[0];
        int y = point[1];
        int count = 0;
        while (x != 0) {
            x--;
            String current = pipes.get(y).get(x);
            if (isInLoop(loop,y,x) && crossPipe.contains(current)) {
                count++;
            }
        }
        return count % 2 != 0;
    }
}

