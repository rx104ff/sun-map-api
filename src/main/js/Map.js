import 'mapbox-gl/dist/mapbox-gl.css';
import 'mapbox-gl/dist/svg/mapboxgl-ctrl-compass.svg';
import 'mapbox-gl/dist/svg/mapboxgl-ctrl-geolocate.svg';
import 'mapbox-gl/dist/svg/mapboxgl-ctrl-zoom-in.svg';
import 'mapbox-gl/dist/svg/mapboxgl-ctrl-zoom-out.svg';
import React from 'react';
import Navbar from './Navbar'
const geo = require('mapbox-geocoding');
geo.setAccessToken('pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4M29iazA2Z2gycXA4N2pmbDZmangifQ.-g_vE53SD2WrJ6tFX7QHmA');

const mapboxgl = require('mapbox-gl')
mapboxgl.accessToken = 'pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4M29iazA2Z2gycXA4N2pmbDZmangifQ.-g_vE53SD2WrJ6tFX7QHmA';

const options = [{
    name: 'Population',
    description: 'Estimated total population',
    property: 'pop_est',
    stops: [
        [0, '#f8d5cc'],
        [1000000, '#f4bfb6'],
        [5000000, '#f1a8a5'],
        [10000000, '#ee8f9a'],
        [50000000, '#ec739b'],
        [100000000, '#dd5ca8'],
        [250000000, '#c44cc0'],
        [500000000, '#9f43d7'],
        [1000000000, '#6e40e6']
    ]
}, {
    name: 'GDP',
    description: 'Estimate total GDP in millions of dollars',
    property: 'gdp_md_est',
    stops: [
        [0, '#f8d5cc'],
        [1000, '#f4bfb6'],
        [5000, '#f1a8a5'],
        [10000, '#ee8f9a'],
        [50000, '#ec739b'],
        [100000, '#dd5ca8'],
        [250000, '#c44cc0'],
        [5000000, '#9f43d7'],
        [10000000, '#6e40e6']
    ]
}]


class Map extends React.Component {


    map;
    constructor(props) {
        super(props);
        this.state = {
            lng: 5,
            lat: 34,
            zoom: 1.5
        };
    }

    componentDidMount() {
        const { lng, lat, zoom } = this.state;

        this.map = new mapboxgl.Map({
            container: this.mapContainer,
            style: 'mapbox://styles/mapbox/streets-v9',
            center: [lng, lat],
            zoom
        });

        this.map.addControl(new mapboxgl.NavigationControl());

        this.map.on('move', () => {
            const { lng, lat } = this.map.getCenter();

            this.setState({
                lng: lng.toFixed(4),
                lat: lat.toFixed(4),
                zoom: this.map.getZoom().toFixed(2)
            });
        });




        this.map.on('load', ()=>{
            const test =  geo.geocode('mapbox.places', 'Dam Square, Amsterdam', function (err, geoData) {
                return geoData;
            });
            this.map.addSource('countries', {
                type: 'geojson',
                data: 'https://cdn.rawgit.com/mapbox/mapbox-react-examples/master/data-overlay/src/data.json'
            });
            this.map.addLayer({
                'id': 'population',
                'type': 'circle',
                'source': {
                    type: 'vector',
                    url: 'mapbox://examples.8fgz4egr'
                },
                'source-layer': 'sf2010',
                'paint': {
                    // make circles larger as the user zooms from z12 to z22
                    'circle-radius': {
                        'base': 1.75,
                        'stops': [[12, 2], [22, 180]]
                    },
                    // color circles by ethnicity, using data-driven styles
                    'circle-color': {
                        property: 'ethnicity',
                        type: 'categorical',
                        stops: [
                            ['Generator', '#fbb03b'],
                            ['Consumer', '#223b53']]
                    }
                }
            });

        });
    }

    render() {

        const { lng, lat, zoom } = this.state;
        const style = {
            top: 0,
            bottom: 0,
            width: '100%'
        };

        return (
            <div>
                <div className="inline-block absolute top left z1 round-full">
                    <Navbar/>
                </div>
                <div  style={style} ref={el => this.mapContainer = el} className="absolute top right left bottom" />
            </div>
        );
    }
}

export default Map
