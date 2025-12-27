public class DeliveryEvaluationServiceImpl implements DeliveryEvaluationService {

    private final DeliveryEvaluationRepository evalRepo;
    private final VendorRepository vendorRepo;
    private final SLARequirementRepository slaRepo;

    public DeliveryEvaluationServiceImpl(
            DeliveryEvaluationRepository evalRepo,
            VendorRepository vendorRepo,
            SLARequirementRepository slaRepo) {
        this.evalRepo = evalRepo;
        this.vendorRepo = vendorRepo;
        this.slaRepo = slaRepo;
    }

    @Override
    public DeliveryEvaluation createEvaluation(DeliveryEvaluation e) {

        Vendor v = vendorRepo.findById(e.getVendor().getId())
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        if (!v.getActive()) {
            throw new IllegalStateException("active vendors");
        }

        SLARequirement sla = slaRepo.findById(e.getSlaRequirement().getId())
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        if (e.getActualDeliveryDays() < 0) {
            throw new IllegalArgumentException(">= 0");
        }

        if (e.getQualityScore() < 0 || e.getQualityScore() > 100) {
            throw new IllegalArgumentException("between 0 and 100");
        }

        e.setMeetsDeliveryTarget(
                e.getActualDeliveryDays() <= sla.getMaxDeliveryDays());

        e.setMeetsQualityTarget(
                e.getQualityScore() >= sla.getMinQualityScore());

        return evalRepo.save(e);
    }

    @Override
    public List<DeliveryEvaluation> getEvaluationsForVendor(Long vendorId) {
        return evalRepo.findByVendorId(vendorId);
    }

    @Override
    public List<DeliveryEvaluation> getEvaluationsForRequirement(Long slaId) {
        return evalRepo.findBySlaRequirementId(slaId);
    }
}
