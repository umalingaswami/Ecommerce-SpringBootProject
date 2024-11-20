import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import com.sun.management.OperatingSystemMXBean;
import com.jtspringproject.JtSpringProject.models.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserProfiling {
    @Test
    public void testUserOperations() {
        // Initialize memory monitoring
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        
        // Initial memory state
        System.out.println("\n=== Initial Memory State ===");
        printMemoryStats(memoryBean);

        long startTime = System.nanoTime();
        long totalCreationTime = 0;
        long totalSortingTime = 0;
        long totalSearchTime = 0;
        
        // Force garbage collection before starting
        System.gc();
        long initialMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        // Run the test multiple times to get meaningful measurements
        for (int i = 0; i < 10000; i++) {
            List<User> users = new ArrayList<>();
            HashMap<String, User> userMap = new HashMap<>();
            
            // Measure object creation time and memory
            long creationStart = System.nanoTime();
            for (int j = 0; j < 1000; j++) {
                User user = new User();
                user.setId(j);
                user.setUsername("User" + j);
                user.setEmail("user" + j + "@example.com");
                users.add(user);
                userMap.put("User" + j, user);
            }
            totalCreationTime += System.nanoTime() - creationStart;

            // Measure sorting time
            long sortStart = System.nanoTime();
            users.sort(Comparator.comparing(User::getUsername));
            totalSortingTime += System.nanoTime() - sortStart;
            
            // Measure search time
            long searchStart = System.nanoTime();
            for (int j = 0; j < users.size(); j++) {
                User foundUser = userMap.get("User" + j);
                assertNotNull(foundUser, "User should be found");
                assertEquals("User" + j, foundUser.getUsername(), "Username should match");
                assertEquals(j, foundUser.getId(), "User ID should match");
            }
            totalSearchTime += System.nanoTime() - searchStart;

            // Print progress every 1000 iterations
            if (i % 1000 == 0) {
                System.out.println("\n=== Progress: " + i + " iterations ===");
                printMemoryStats(memoryBean);
            }
        }

        // Force garbage collection before final measurement
        System.gc();
        long finalMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long totalTime = System.nanoTime() - startTime;
        
        // Print final results
        System.out.println("\n====== Performance Results ======");
        System.out.println("Time Metrics:");
        System.out.println("Total time: " + totalTime / 1_000_000 + " ms");
        System.out.println("Average creation time: " + (totalCreationTime / 10000) / 1_000_000 + " ms");
        System.out.println("Average sorting time: " + (totalSortingTime / 10000) / 1_000_000 + " ms");
        System.out.println("Average search time: " + (totalSearchTime / 10000) / 1_000_000 + " ms");

        System.out.println("\nMemory Metrics:");
        System.out.println("Initial memory used: " + (initialMemory / 1024 / 1024) + " MB");
        System.out.println("Final memory used: " + (finalMemory / 1024 / 1024) + " MB");
        System.out.println("Memory difference: " + ((finalMemory - initialMemory) / 1024 / 1024) + " MB");
        
        System.out.println("\nSystem Resources:");
        System.out.println("System CPU Load: " + String.format("%.2f%%", osBean.getSystemCpuLoad() * 100));
        System.out.println("Process CPU Time: " + osBean.getProcessCpuTime() + " ns");
        System.out.println("Process CPU Load: " + String.format("%.2f%%", osBean.getProcessCpuLoad() * 100));
        System.out.println("Available Processors: " + osBean.getAvailableProcessors());
        System.out.println("Total Physical Memory: " + (osBean.getTotalPhysicalMemorySize() / 1024 / 1024) + " MB");
        System.out.println("Free Physical Memory: " + (osBean.getFreePhysicalMemorySize() / 1024 / 1024) + " MB");
        
        // Final memory state
        System.out.println("\n=== Final Memory State ===");
        printMemoryStats(memoryBean);
    }

    private void printMemoryStats(MemoryMXBean memoryBean) {
        MemoryUsage heapUsage = memoryBean.getHeapMemoryUsage();
        MemoryUsage nonHeapUsage = memoryBean.getNonHeapMemoryUsage();
        
        System.out.println("Heap Memory Usage:");
        System.out.println("Used: " + (heapUsage.getUsed() / 1024 / 1024) + " MB");
        System.out.println("Committed: " + (heapUsage.getCommitted() / 1024 / 1024) + " MB");
        System.out.println("Max: " + (heapUsage.getMax() / 1024 / 1024) + " MB");
        
        System.out.println("\nNon-Heap Memory Usage:");
        System.out.println("Used: " + (nonHeapUsage.getUsed() / 1024 / 1024) + " MB");
        System.out.println("Committed: " + (nonHeapUsage.getCommitted() / 1024 / 1024) + " MB");
    }
}