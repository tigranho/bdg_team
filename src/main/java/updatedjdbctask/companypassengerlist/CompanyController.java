package updatedjdbctask.companypassengerlist;


import updatedjdbctask.exceptions.DatabaseException;
import updatedjdbctask.model.Company;
import updatedjdbctask.service.CompanyService;
import updatedjdbctask.service.serviceimpl.CompanyServiceImpl;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by User on 18.09.2020.
 */
public class CompanyController {
    private CompanyService companyService;
    private CompanyPassengerList companyPassengerList;

    public CompanyController() {
        this.companyService = new CompanyServiceImpl();
        this.companyPassengerList = new CompanyPassengerList();
    }

    public void addCompaniesFromFileList() throws DatabaseException, SQLException {
        List<Company> list = companyPassengerList.getCompaniesList();
        for (Company company : list) {
            this.companyService.save(company);
        }
    }

    public static void main(String[] args) throws DatabaseException, SQLException {
        CompanyController companyController = new CompanyController();
        companyController.addCompaniesFromFileList();
    }
}
