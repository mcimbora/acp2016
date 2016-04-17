import java.io.File;
import java.io.IOException;

import org.optaplanner.acp2016.io.TorpedoRotationIO;
import org.optaplanner.core.api.domain.solution.Solution;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;

public class Main {

   public static void main(String[] args) throws IOException {

      SolverFactory solverFactory = SolverFactory.createFromXmlResource("org/optaplanner/acp2016/solver/torpedoRotationSolverConfig.xml");
      Solver solver = solverFactory.buildSolver();

      TorpedoRotationIO torpedoRotationIO = new TorpedoRotationIO();
      Solution solution = torpedoRotationIO.read(new File(args[0]));

      solver.solve(solution);

      torpedoRotationIO.write(solver.getBestSolution(), new File(args[1]));
   }
}
