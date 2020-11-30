import com.galvanize.accessoriesgradle.controllers.AccessoriesController
import com.galvanize.accessoriesgradle.entities.Accessories
import com.galvanize.accessoriesgradle.repositories.AccessoriesRepository
import spock.lang.Specification

class AccessoriesControllerSpec extends Specification {

    AccessoriesController accessoriesController = new AccessoriesController()

    def setup() {
        accessoriesController.accessoriesRepository = Mock(AccessoriesRepository)
    }

    def "Repository get all method should return something"() {
        given: "A request has been received to get all accessory data"
        accessoriesController.accessoriesRepository.findAll() >> mockData

        when: "We process the request"
        Iterable<Accessories> result = accessoriesController.getAllAccessories()

        then:
        result == mockData

        where:
        mockData = []

    }

    def "Repository get by id method should throw exception if id is not found"() {
        given: "A request has been received to get data by id"
        accessoriesController.accessoriesRepository.findById(_) >> null

        when: "We process the req"
        accessoriesController.getAccessoriesById(1)

        then:
        thrown RuntimeException
    }

    def "Repository post method should not throw exception"() {
        given: "A create requestion has been received"
        Accessories sampleAccessory = new Accessories()
        sampleAccessory.setId(1)
        sampleAccessory.setMake("")
        sampleAccessory.setModel("")
        sampleAccessory.setWeight(1)
        sampleAccessory.setLength(1)
        sampleAccessory.setWidth(1)
        sampleAccessory.setHeight(1)
        sampleAccessory.setRating(1)
        sampleAccessory.setFullPrice(1)
        sampleAccessory.setImageURL("")
        sampleAccessory.setCan_delete(false)
        accessoriesController.accessoriesRepository.save(_) >> sampleAccessory.setCan_delete(true)

        when:
        Accessories response = accessoriesController.createAccessories(sampleAccessory)

        then:
        response == sampleAccessory.setCan_delete(true)
    }

    def "Repository patch method should throw exception if id of request body is not found in db"() {
        given:
        Accessories sampleAccessory = new Accessories()
        sampleAccessory.setId(1)
        sampleAccessory.setMake("")
        sampleAccessory.setModel("")
        sampleAccessory.setWeight(1)
        sampleAccessory.setLength(1)
        sampleAccessory.setWidth(1)
        sampleAccessory.setHeight(1)
        sampleAccessory.setRating(1)
        sampleAccessory.setFullPrice(1)
        sampleAccessory.setImageURL("")
        sampleAccessory.setCan_delete(false)
        accessoriesController.accessoriesRepository.findById(_) >> []

        when:
        accessoriesController.updateAccessoriesRating(1, sampleAccessory)

        then:
        thrown RuntimeException
    }

    def "Repository delete method should throw exception if id of req body is not found in db"() {
        given:
        Accessories sampleAccessory = new Accessories()
        sampleAccessory.setId(1)
        sampleAccessory.setMake("")
        sampleAccessory.setModel("")
        sampleAccessory.setWeight(1)
        sampleAccessory.setLength(1)
        sampleAccessory.setWidth(1)
        sampleAccessory.setHeight(1)
        sampleAccessory.setRating(1)
        sampleAccessory.setFullPrice(1)
        sampleAccessory.setImageURL("")
        sampleAccessory.setCan_delete(false)
        accessoriesController.accessoriesRepository.findById(_) >> []

        when:
        accessoriesController.deleteAccessoriesById(1)

        then:
        thrown RuntimeException
    }
}
