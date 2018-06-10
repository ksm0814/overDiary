package overdiary.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
    List<Alarm> findByTargetUser(User targetUser);
    List<Alarm> findByTargetUserAndIsOpened(User targetUser, boolean isOpened);
}
