package pe.edu.unmsm.fisi.upg.ads.dirtycode.infrastructure;

import java.util.ArrayList;
import java.util.List;
import pe.edu.unmsm.fisi.upg.ads.dirtycode.domain.IRepository;
import pe.edu.unmsm.fisi.upg.ads.dirtycode.domain.Speaker;

public class SqlServerRepository implements IRepository {

    public int saveSpeaker(Speaker speaker) {
        // TODO: Save speaker to SQL Server DB. For now, just assume success and return 1.
        return 1;
    }

    public int getRegistrationFee(int yearsExperience) {

        //TODO: Create a table Fee and populate it wih the data that is bellow
        int registrationFee = 0;
        List<FeeTable> feeFakeData = new ArrayList<FeeTable>();

        feeFakeData.add(new FeeTable(500, 0, 1));
        feeFakeData.add(new FeeTable(250, 2, 3));
        feeFakeData.add(new FeeTable(100, 4, 5));
        feeFakeData.add(new FeeTable(50, 6, 9));

        for (FeeTable fee : feeFakeData) {

            if (yearsExperience > fee.getMinYearExperience()
                    && fee.getMaxYearExperience() <= fee.getMaxYearExperience()) {
                registrationFee = fee.getFee();
                break;
            }
        }

        return registrationFee;
    }
}
