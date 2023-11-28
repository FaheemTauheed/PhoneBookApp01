package in.ashokit.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.ContactEntity;

public interface ContactDtlsRepository extends JpaRepository<ContactEntity, Serializable> {

}
