package LUIO.luiobackend.domain.user.repository;

import LUIO.luiobackend.domain.user.entity.User;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private static final String COLLECTION_NAME = "users";

    public void saveUser(User user) {
        Firestore firestore = FirestoreClient.getFirestore();

        firestore.collection(COLLECTION_NAME).document(user.getUserName()).set(user);

    }
}
