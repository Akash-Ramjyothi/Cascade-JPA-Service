package com.jpa.mappings.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "instructor")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    /**
     * One-to-One mapping with InstructorDetail
     * CascadeType.ALL -> propagates all operations
     * FetchType.LAZY -> improves performance (loads only when needed)
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_detail_id", nullable = false)
    private InstructorDetail instructorDetail;

    // Default constructor (required by JPA)
    public Instructor() {
    }

    // Parameterized constructor
    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // =======================
    // Convenience Method
    // =======================
    public void addInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }

    // =======================
    // Getters & Setters
    // =======================
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }

    // =======================
    // Lifecycle Callbacks
    // =======================
    @PrePersist
    public void prePersist() {
        System.out.println("📌 About to persist Instructor: " + email);
    }

    @PreRemove
    public void preRemove() {
        System.out.println("⚠️ Deleting Instructor: " + id);
    }

    // =======================
    // toString (safe for LAZY loading)
    // =======================
    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
        // ⚠️ Avoid printing instructorDetail to prevent LazyInitializationException
    }
}
