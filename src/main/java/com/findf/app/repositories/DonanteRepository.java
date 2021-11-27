package com.findf.app.repositories;

import org.springframework.data.mongodb.repository.*;
import org.springframework.stereotype.Repository;
import com.findf.app.models.*;

@Repository
public interface DonanteRepository extends MongoRepository<DonanteModel,String>{

}