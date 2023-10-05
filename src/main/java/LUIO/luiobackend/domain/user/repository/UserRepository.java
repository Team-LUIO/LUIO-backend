package LUIO.luiobackend.domain.user.repository;

import LUIO.luiobackend.domain.user.entity.User;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;


@Repository
public class UserRepository {
    private static final String COLLECTION_NAME = "users";

    public void saveUser(User user) {
        Firestore firestore = FirestoreClient.getFirestore();

        firestore.collection(COLLECTION_NAME).document(user.getUserName()).set(user);

    }

	public void deleteUser( String userName ) throws Exception {
		Firestore firestore = FirestoreClient.getFirestore();
		firestore.collection(COLLECTION_NAME).document(userName).delete().get(2, TimeUnit.SECONDS);
	}
}
