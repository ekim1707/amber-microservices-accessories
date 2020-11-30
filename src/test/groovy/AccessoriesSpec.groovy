import com.galvanize.accessoriesgradle.entities.Accessories
import spock.lang.Specification

class AccessoriesSpec extends Specification {

    Accessories accessory = new Accessories()

    def setup() {
        accessory.setId(1)
        accessory.setMake("Apple")
        accessory.setModel("iPhone")
        accessory.setWeight(1)
        accessory.setLength(1)
        accessory.setWidth(1)
        accessory.setHeight(1)
        accessory.setRating(1)
        accessory.setFullPrice(1)
        accessory.setImageURL("url")
        accessory.setCan_delete(true)
    }

    def "testing getters and setters"() {
        given:

        when:
        Integer id = accessory.getId()
        String make = accessory.getMake()
        String model = accessory.getModel()
        Double weight = accessory.getWeight()
        Double length = accessory.getLength()
        Double width = accessory.getWidth()
        Double height = accessory.getHeight()
        Integer rating = accessory.getRating()
        Double fullPrice = accessory.getFullPrice()
        String imageURL = accessory.getImageURL()
        boolean can_delete = accessory.isCan_delete()

        then:
        id == 1
        make == "Apple"
        model == "iPhone"
        weight == 1
        length == 1
        width == 1
        height == 1
        rating == 1
        fullPrice == 1
        imageURL == "url"
        can_delete == true

    }
}
