package Delphix;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import Delphix.Models.DepartingTrain;
import Delphix.Models.Estimate;
import Delphix.Models.Etd;
import Delphix.Models.Station;

/*
 * BartRealTimeEstimator - has a real-time 
 * information feed containing information about real-time estimated 
 * departures for specific stations
 */
public class BartRealTimeEstimator {

	private static final DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a z");

	/*
	 * preparing a final list where every train leaves to respective destination
	 * from source
	 */
	public void printDepartingTrains(List<Station> stationList, int trainLimit) throws BartException {
		List<DepartingTrain> estimateTimeOfDep = new ArrayList<DepartingTrain>();
		List<DepartingTrain> fList = null;
		try {
			if (stationList != null && stationList.size() > 0) {
				for (Station st : stationList) {
					if (st != null && st.getEtd() != null && st.getEtd().size() > 0) {
						for (Etd etd : st.getEtd()) {
							if (etd != null && etd.getEstimate() != null && etd.getEstimate().size() > 0) {
								for (Estimate est : etd.getEstimate()) {
									DepartingTrain dpTrain = new DepartingTrain();
									dpTrain.setStation(st.getName());
									dpTrain.setFinalEtd(est.getFinalEtd());
									dpTrain.setDestination(etd.getDestination());
									estimateTimeOfDep.add(dpTrain);
								}
							}
						}
					}
				}

				Date date = new Date();
				System.out.println(" --------------------------------------------------");
				System.out.println("  " + stationList.get(0).getName() + " " + dateFormat.format(date));
				System.out.println(" --------------------------------------------------");

				if (estimateTimeOfDep != null) {
					Collections.sort(estimateTimeOfDep);
					if (estimateTimeOfDep.size() > 10) {
						fList = estimateTimeOfDep.subList(0, trainLimit);
					} else {
						fList = estimateTimeOfDep;
					}
				}

				if (fList != null && fList.size() > 0) {
					for (DepartingTrain dT : fList) {
						System.out.println(dT.getFinalEtd() + " " + dT.getDestination());
					}
				} else {
					System.out.println("Updates are temporary not available");
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new BartException(BartExceptionCodes.INTERNAL_SERVER_ERROR.getCode(),
					BartExceptionCodes.INTERNAL_SERVER_ERROR.getMessage());
		}
	}

	public void estimator(String stationCode, int trainLimit) throws Exception {
		try {
			List<Station> stationList = BartApi.parseDepartureJson(stationCode);
			printDepartingTrains(stationList, trainLimit);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
