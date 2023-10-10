package LUIO.luiobackend.domain.user.repository;

import LUIO.luiobackend.domain.user.entity.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Repository
public class UserRepository {
    private static final String COLLECTION_NAME = "users";

    public void saveUser( User user ) {
        Firestore firestore = FirestoreClient.getFirestore();

        firestore.collection( COLLECTION_NAME ).document( user.getUserName() ).set( user );

    }

    public List<User> findAllUsers() throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = firestore.collection( COLLECTION_NAME ).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<User> userList = new ArrayList<>();
        for ( QueryDocumentSnapshot document : documents ) {
            userList.add(new User(document.getId(),(String)document.get("userMbti")
                    ,(String)document.get("userImageUrl"), (String)document.get("userIntroduce")));
        }

        return userList;
    }

    public void deleteUser( String userName ) throws InterruptedException, ExecutionException, TimeoutException {
        Firestore firestore = FirestoreClient.getFirestore();
        firestore.collection( COLLECTION_NAME ).document( userName ).delete().get(2, TimeUnit.SECONDS);
    }

    public User findByUserName(String userName) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentSnapshot documentSnapshot = firestore.collection( COLLECTION_NAME ).document( userName ).get().get();
        if(documentSnapshot.exists()) {
            return new User(documentSnapshot.getId(), (String)documentSnapshot.get("userMbti")
                    ,(String)documentSnapshot.get("userImageUrl"), (String)documentSnapshot.get("userIntroduce") );
        }
        else {
            throw new IllegalArgumentException( "잘못된 이름이 입력되었습니다." );
        }
    }
}
