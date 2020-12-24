import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    public static void main(String args[]) throws Exception {

        InputStreamReader console = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(console);
        StringBuilder output = new StringBuilder();
        int numTestCases = Integer.parseInt(reader.readLine());
        reader.readLine();

        for (int x = 0; x < numTestCases; x++) {
            //
            //Set<Integer> fireStations = new HashSet<Integer>();
            String[] info = reader.readLine().split(" ");
            int numFireStations = Integer.parseInt(info[0]);
            int numIntersections = Integer.parseInt(info[1]);
            List<List<Node>> adjList = new ArrayList<List<Node>>();
            
            for (int y = 0; y <= numIntersections; y++) {
                List<Node> intersection = new ArrayList<Node>();
                adjList.add(intersection);
            }
            for (int y = 0; y < numFireStations; y++) {
                int temp = Integer.parseInt(reader.readLine());
                //fireStations.add(temp);
                adjList.get(0).add(new Node(temp, 0));
                adjList.get(temp).add(new Node(0, 0));
            }
            String intersectionInfo;
            
            while (!(intersectionInfo = reader.readLine()).equals("")) {
                String[] infoSplit = intersectionInfo.split(" ");
                adjList.get(Integer.parseInt(infoSplit[0])).add(new Node(Integer.parseInt(infoSplit[1]), Integer.parseInt(infoSplit[2])));
                adjList.get(Integer.parseInt(infoSplit[1])).add(new Node(Integer.parseInt(infoSplit[0]), Integer.parseInt(infoSplit[2])));
            }
            
            int val = Integer.MAX_VALUE;
            int valIndex = 0;
            for (int y = 1; y <= numIntersections; y++) {
                //if (fireStations.contains(numIntersections))
                //    continue;
                DPQ dpq = new DPQ(numIntersections + 1);
                adjList.get(0).add(new Node(y, 0));
                adjList.get(y).add(new Node(0, 0));
                /*for (int ys = 0; ys <= numIntersections; ys++) {
                    System.out.print(ys + " -> ");
                    for (int z = 0; z < adjList.get(ys).size(); z++)
                        System.out.print(adjList.get(ys).get(z).node + ", ");
                    System.out.println();
                }*/
                dpq.dijkstra(adjList, 0);
                int maxDistance = dpq.maxValue();
                if (val > maxDistance) {
                    val = maxDistance;
                    valIndex = y;
                }
                //val = Math.min(val, dpq.maxDist);
                //for (int i = 0; i < dpq.dist.length; i++) 
                //    System.out.println(0 + " to " + i + " is "
                //               + dpq.dist[i]); 
                adjList.get(0).remove(adjList.get(0).size() - 1);
                adjList.get(y).remove(adjList.get(y).size() - 1);
                //System.out.println("Val: " + val);
                //System.out.println("Val Index: " + valIndex);
            }
            output.append(valIndex + "\n");
        }
        System.out.print(output.toString());
    }
}

//DPQ class code retreived from GeeksForGeeks
class DPQ { 
    int dist[]; 
    private Set<Integer> settled; 
    private PriorityQueue<Node> pq; 
    private int V;
    List<List<Node> > adj; 
  
    DPQ(int V) 
    { 
        this.V = V; 
        dist = new int[V]; 
        settled = new HashSet<Integer>(); 
        pq = new PriorityQueue<Node>(V, new Node()); 
    } 
    public int maxValue() {
        int maxVal = Integer.MIN_VALUE;
        for (int x = 0; x < dist.length; x++) {
            maxVal = Math.max(maxVal, dist[x]);
        }
        return maxVal;
    }
    public void dijkstra(List<List<Node> > adj, int src) 
    { 
        this.adj = adj; 
  
        for (int i = 0; i < V; i++) 
            dist[i] = Integer.MAX_VALUE; 
  
        // Add source node to the priority queue 
        pq.add(new Node(src, 0)); 

        dist[src] = 0; 
        while (settled.size() != V) { 
  
            int u = pq.remove().node; 

            settled.add(u); 
  
            e_Neighbours(u); 
        } 
    } 

    private void e_Neighbours(int u) 
    { 
        int edgeDistance = -1; 
        int newDistance = -1; 
  
        // All the neighbors of v 
        for (int i = 0; i < adj.get(u).size(); i++) { 
            Node v = adj.get(u).get(i); 

            if (!settled.contains(v.node)) { 
                edgeDistance = v.cost; 
                newDistance = dist[u] + edgeDistance; 

                if (newDistance < dist[v.node])
                    dist[v.node] = newDistance;

                pq.add(new Node(v.node, dist[v.node])); 
            } 
        } 
    } 
}

class Node implements Comparator<Node> { 
    public int node; 
    public int cost; 
    
    public Node() {
    }

    public Node(int node, int cost) 
    { 
        this.node = node; 
        this.cost = cost; 
    } 

    @Override
    public int compare(Node node1, Node node2) 
    { 
        if (node1.cost < node2.cost) 
            return -1; 
        if (node1.cost > node2.cost) 
            return 1; 
        return 0; 
    } 
} 