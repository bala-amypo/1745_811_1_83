public class SLARequirementServiceImpl implements SLARequirementService {

    private final SLARequirementRepository repository;

    public SLARequirementServiceImpl(SLARequirementRepository repository) {
        this.repository = repository;
    }

    @Override
    public SLARequirement createRequirement(SLARequirement r) {
        if (r.getMaxDeliveryDays() == null || r.getMaxDeliveryDays() <= 0) {
            throw new IllegalArgumentException("Max delivery days");
        }
        if (r.getMinQualityScore() < 0 || r.getMinQualityScore() > 100) {
            throw new IllegalArgumentException("Quality score");
        }
        if (repository.existsByRequirementName(r.getRequirementName())) {
            throw new IllegalArgumentException("unique");
        }
        return repository.save(r);
    }

    @Override
    public SLARequirement updateRequirement(Long id, SLARequirement update) {
        SLARequirement existing = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        if (update.getRequirementName() != null &&
            repository.existsByRequirementName(update.getRequirementName())) {
            throw new IllegalArgumentException("unique");
        }

        if (update.getRequirementName() != null)
            existing.setRequirementName(update.getRequirementName());

        return repository.save(existing);
    }

    @Override
    public void deactivateRequirement(Long id) {
        SLARequirement r = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found"));
        r.setActive(false);
        repository.save(r);
    }
}
