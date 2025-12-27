public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public Vendor createVendor(Vendor vendor) {
        if (vendorRepository.existsByName(vendor.getName())) {
            throw new IllegalArgumentException("unique");
        }
        return vendorRepository.save(vendor);
    }

    @Override
    public Vendor updateVendor(Long id, Vendor update) {
        Vendor existing = vendorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        if (update.getName() != null &&
            !update.getName().equals(existing.getName()) &&
            vendorRepository.existsByName(update.getName())) {
            throw new IllegalArgumentException("unique");
        }

        if (update.getContactEmail() != null)
            existing.setContactEmail(update.getContactEmail());

        if (update.getContactPhone() != null)
            existing.setContactPhone(update.getContactPhone());

        return vendorRepository.save(existing);
    }

    @Override
    public Vendor getVendorById(Long id) {
        return vendorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found"));
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public void deactivateVendor(Long id) {
        Vendor vendor = getVendorById(id);
        vendor.setActive(false);
        vendorRepository.save(vendor);
    }
}
