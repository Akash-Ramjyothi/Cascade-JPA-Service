package com.jpa.mappings;

import com.jpa.mappings.dao.AppDAO;
import com.jpa.mappings.entity.Instructor;
import com.jpa.mappings.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MappingsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MappingsApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {

            System.out.println("\n===== 🚀 STARTING APPLICATION RUNNER =====\n");

            try {
                // Toggle operations as needed
                // createInstructor(appDAO);
                // findInstructor(appDAO);
                deleteInstructor(appDAO);

            } catch (Exception ex) {
                System.err.println("❌ ERROR OCCURRED: " + ex.getMessage());
                ex.printStackTrace();
            }

            System.out.println("\n===== ✅ APPLICATION RUN COMPLETED =====\n");
        };
    }

    /**
     * Create Instructor with associated InstructorDetail
     */
    private void createInstructor(AppDAO appDAO) {

        Instructor instructor = new Instructor(
                "Madhu", "Patel", "madhu@luv2code.com"
        );

        InstructorDetail instructorDetail = new InstructorDetail(
                "http://www.luv2code.com/youtube",
                "Guitar"
        );

        // Associate objects
        instructor.setInstructorDetail(instructorDetail);

        System.out.println("💾 Saving Instructor: " + instructor);
        System.out.println("🔗 Associated Detail: " + instructorDetail);

        appDAO.save(instructor);

        System.out.println("✅ Instructor saved successfully!\n");
    }

    /**
     * Fetch Instructor by ID
     */
    private void findInstructor(AppDAO appDAO) {

        int id = 2;

        System.out.println("🔍 Fetching Instructor with ID: " + id);

        Instructor instructor = appDAO.findInstructorById(id);

        if (instructor != null) {
            System.out.println("✅ Instructor Found: " + instructor);
            System.out.println("📎 Instructor Detail: " + instructor.getInstructorDetail());
        } else {
            System.out.println("⚠️ No Instructor found with ID: " + id);
        }
    }

    /**
     * Delete Instructor by ID
     */
    private void deleteInstructor(AppDAO appDAO) {

        int id = 1;

        System.out.println("🗑️ Attempting to delete Instructor with ID: " + id);

        try {
            appDAO.deleteInstructorById(id);
            System.out.println("✅ Instructor deleted successfully!");
        } catch (Exception ex) {
            System.err.println("❌ Failed to delete Instructor: " + ex.getMessage());
        }
    }
}
