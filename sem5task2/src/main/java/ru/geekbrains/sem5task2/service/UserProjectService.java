package ru.geekbrains.sem5task2.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.sem5task2.model.Project;
import ru.geekbrains.sem5task2.model.ProjectUsers;
import ru.geekbrains.sem5task2.model.User;
import ru.geekbrains.sem5task2.repository.ProjectRepository;
import ru.geekbrains.sem5task2.repository.UserRepository;
import ru.geekbrains.sem5task2.repository.UsersProjectRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserProjectService {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final UsersProjectRepository usersProjectRepository;
    @Autowired
    public UserProjectService(UserRepository userRepository, ProjectRepository projectRepository,
                              UsersProjectRepository usersProjectRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.usersProjectRepository = usersProjectRepository;
    }

    public List<User> getUsersByProjectId(Long projectId){
        List<ProjectUsers> pu =  usersProjectRepository.findUsersByProjectId(projectId);
        return pu.stream().map(usersProject -> userRepository.findById(usersProject.getUser().getId()).orElse(null))
                .collect(Collectors.toList());
    }
    public List<Project> getProjectsByUserId(Long userId){
        List<ProjectUsers> pu =  usersProjectRepository.findAllByUserId(userId);
        System.out.println("Список проектов с пользователем " + userId + " " + pu.size());
        return pu.stream().map(usersProject -> projectRepository.findById(usersProject.getProject().getId()).orElse(null))
                .collect(Collectors.toList());
    }
    @Transactional
    public void addUserToProject(Long userId, Long projectId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("Пользователь с id " + userId + " не найден"));
        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new RuntimeException("Проект с id " + projectId + " не найден"));
        if (usersProjectRepository.existsByUserIdAndProjectId(userId, projectId)) {
            throw new RuntimeException("Связь между пользователем и проектом уже существует");
        }
        ProjectUsers pu = new ProjectUsers();
        pu.setUser(user);
        pu.setProject(project);
        pu.setRelatedEntityId(1L);
        System.out.println(pu);
        usersProjectRepository.save(pu);
    }

    public void removeUserFromProject(Long userId, Long projectId){
        List<ProjectUsers> pul = usersProjectRepository.findUsersByProjectId(projectId);
        pul.stream().filter(projectUsers -> projectUsers.getUser().getId().equals(userId)).findFirst()
                .ifPresent(pu -> usersProjectRepository.deleteById(pu.getId()));
    }
}
