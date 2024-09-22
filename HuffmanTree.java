import java.util.HashMap;
import java.util.PriorityQueue;

class HuffmanTree {
    private HuffmanNode root;

    public HuffmanTree(HashMap<Byte, Integer> frequencyMap) {
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>((a, b) -> a.frequency - b.frequency);
        for (var entry : frequencyMap.entrySet()) {
            pq.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            HuffmanNode combined = new HuffmanNode((byte) 0, left.frequency + right.frequency);
            combined.left = left;
            combined.right = right;
            pq.add(combined);
        }
        root = pq.poll();
    }

    public HuffmanNode getRoot() {
        return root;
    }
}
