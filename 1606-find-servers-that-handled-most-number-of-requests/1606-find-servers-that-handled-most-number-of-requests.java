

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        // Available servers
        TreeSet<Integer> available = new TreeSet<>();

        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        // Busy servers: {finishTime, serverIndex}
        PriorityQueue<int[]> busy = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        int[] count = new int[k];

        for (int i = 0; i < arrival.length; i++) {

            // Free all servers whose requests are completed
            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                int server = busy.poll()[1];
                available.add(server);
            }

            // If no server is available, drop the request
            if (available.isEmpty()) {
                continue;
            }

            // Preferred server: i % k
            int preferred = i % k;

            // Find the smallest available server >= preferred
            Integer server = available.ceiling(preferred);

            // If not found, wrap around to the smallest server
            if (server == null) {
                server = available.first();
            }

            // Assign request to server
            available.remove(server);

            count[server]++;

            // Server becomes free at arrival[i] + load[i]
            busy.offer(new int[]{
                arrival[i] + load[i], server
            });
        }

        // Find maximum number of requests handled
        int max = 0;

        for (int c : count) {
            max = Math.max(max, c);
        }

        // Collect all busiest servers
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == max) {
                result.add(i);
            }
        }

        return result;
    }
}
