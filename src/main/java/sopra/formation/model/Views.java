package sopra.formation.model;

public class Views {
	public static class ViewCommon {}
	
	public static class ViewEvaluation extends ViewCommon {}
	
	public static class ViewPersonne extends ViewCommon {}
	
	public static class ViewStagiaire extends ViewPersonne {}
	
	public static class ViewStagiaireDetail extends ViewStagiaire {}
	
	public static class ViewFormateur extends ViewPersonne {}
	
	public static class ViewFormateurDetail extends ViewFormateur {}
	
	public static class ViewFiliere extends ViewCommon {}
	
	public static class ViewFiliereWithReferent extends ViewFiliere {}
	
	public static class ViewMatiere extends ViewCommon {}
	
	public static class ViewMatiereDetail extends ViewMatiere {}
	
	public static class ViewSalle extends ViewCommon {}
	
	public static class ViewSalleDetail extends ViewSalle {}
	
	public static class ViewUe extends ViewCommon {}
}
