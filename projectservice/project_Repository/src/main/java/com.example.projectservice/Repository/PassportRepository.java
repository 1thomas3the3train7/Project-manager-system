package com.example.projectservice.Repository;

import com.example.projectservice.Model.Passport.BasePassport;
import com.example.projectservice.Model.Project.BaseProject;

public interface PassportRepository {
    void save(BasePassport basePassport);
    void delete(BasePassport basePassport);
    void appendProjectAndPassport(BaseProject baseProject,BasePassport basePassport);
    void appendProjectAndPassport(int project_id,int passport_id);

}
